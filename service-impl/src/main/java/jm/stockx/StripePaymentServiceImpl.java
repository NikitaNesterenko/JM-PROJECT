package jm.stockx;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import jm.stockx.entity.User;
import jm.stockx.util.striperequest.StripePaymentChargeRequest;
import jm.stockx.util.striperequest.StripePaymentChargeRequestWithToken;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Класс бизнес-логики для работы со Stripe объектами.
 */
@Service
@RequiredArgsConstructor
public class StripePaymentServiceImpl {

    /**
     * Для генерации public и secret API keys необходимо зарегистрироваться в https://dashboard.stripe.com
     */
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    private final UserService userService;

    /**
     * Простое создание Stripe пользователя на основании поданного email.
     *
     * @param userEmail Подаваемый email пользователя.
     * @return Созданный Stripe Customer.
     */
    @SneakyThrows
    public Customer createCustomer(String userEmail) {
        getApiKey(secretKey);
        String foundEmail = getUserEmail(userEmail);

        Map<String, Object> params = Map.of("email", foundEmail);
        return Customer.create(params);
    }

    /**
     * Получение Stripe Customer по stripeUserId.
     *
     * @param stripeUserId Уникальный идентификатор Stripe Customer. Можно посмотреть на сайте.
     * @return Найденный Stripe Customer.
     */
    public Customer getCustomer(String stripeUserId)
            throws CardException, APIException,
            AuthenticationException, InvalidRequestException, APIConnectionException {
        getApiKey(secretKey);
        return Customer.retrieve(stripeUserId);
    }

    /**
     * Осуществляет процессинг Stripe Charge.
     *
     * @param stripePaymentChargeRequest Данные для создания Stripe Charge.
     * @return Созданный Stripe Charge.
     */
    public Charge charge(StripePaymentChargeRequestWithToken stripePaymentChargeRequest)
            throws InvalidRequestException, APIConnectionException,
            CardException, APIException, AuthenticationException {
        getApiKey(secretKey);
        Map<String, Object> chargeParams = createStripePaymentParams(stripePaymentChargeRequest);
        return Charge.create(chargeParams);
    }

    /**
     * Осуществляет процессинг Stripe Charge.
     *
     * @param stripePaymentChargeRequest Данные для создания Stripe Charge.
     * @return Созданный Stripe Charge.
     */
    public Charge chargeBackendOnly(StripePaymentChargeRequest stripePaymentChargeRequest)
            throws InvalidRequestException, APIConnectionException,
            CardException, APIException, AuthenticationException {
        getApiKey(secretKey);
        Map<String, Object> chargeParams = createStripePaymentParams(stripePaymentChargeRequest);
        return Charge.create(chargeParams);
    }

    /**
     * Задание apiKey для проведения операций.
     *
     * @param secretKey apiKey, подаваемый из .properties файла.
     */
    private static void getApiKey(String secretKey) {
        Stripe.apiKey = secretKey;
    }

    private String getUserEmail(String userEmail) throws UserNotFoundException {
        User user = userService.getUserByEmail(userEmail);
        return user.getEmail();
    }

    /**
     * Создание параметров карты для включения в токен.
     * Номер карты менять не стоит.
     * Это фиксированный номер, предоставляемый Stripe для тестирования.
     *
     * @return Map с параметрами карты.
     */
    private Map<String, Object> createCardParams() {
        return Map.of(
                "number", "4242424242424242",
                "exp_month", "12",
                "exp_year", "2030",
                "cvc", "111");
    }

    /**
     * Задание параметров токена.
     *
     * @return Map с параметрами токена.
     */
    private Map<String, Object> createTokenParams() {
        return Map.of("card", createCardParams());
    }

    /**
     * Создание токена. Он необходим для обеспечения валидности транзакции.
     * Вмето токена может быть подан Customer id. Тогда токен на основании данных карты можно не генерировать.
     *
     * @return Созданный токен.
     */
    private Token createToken()
            throws CardException, APIException,
            AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> tokenParamsForCreation = createTokenParams();
        return Token.create(tokenParamsForCreation);
    }

    /**
     * Создание объекта транзакции.
     *
     * @param stripePaymentChargeRequest Некоторые параметры транзакции.
     * @return Map с параметрами траназкции.
     */
    @SneakyThrows
    private Map<String, Object> createStripePaymentParams(StripePaymentChargeRequest stripePaymentChargeRequest) {
        return Map.of(
                "amount", stripePaymentChargeRequest.getAmount(),
                "currency", stripePaymentChargeRequest.getCurrency(),
                "description", stripePaymentChargeRequest.getDescription(),
                "source", createToken().getId());
    }
}