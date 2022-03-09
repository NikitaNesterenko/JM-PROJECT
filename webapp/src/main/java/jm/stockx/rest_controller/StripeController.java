package jm.stockx.rest_controller;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import jm.stockx.StripePaymentServiceImpl;
import jm.stockx.dto.user.UserEmailDto;
import jm.stockx.util.*;
import jm.stockx.util.striperequest.StripePaymentChargeRequest;
import jm.stockx.util.striperequest.StripePaymentChargeRequestWithToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripeController {
    private final StripePaymentServiceImpl stripePaymentServiceImpl;

    /**
     * Создание пользователя.
     * Проверить удачность создания можно в https://dashboard.stripe.com/test/customers
     *
     * @param emailDto Подается только email пользователя.
     * @return Возвращается уникальный индетификатор.
     */
    @PostMapping("/api/payment/stripe/customer/create")
    public Response<String> createCustomer(@RequestBody UserEmailDto emailDto) {
        Customer createdCustomer = stripePaymentServiceImpl.createCustomer(emailDto.getEmail());
        return Response.accepted().body(createdCustomer.getId());
    }

    /**
     * Получение пользователя по stripeUserId.
     * Он генерируется при вызове stripePaymentServiceImpl.createCustomer.
     * Без него не получится провести транзакцию(Charge).
     *
     * @param stripeUserId Уникальный Id пользователя.
     * @return Полученный пользователь.
     */
    @PostMapping("/api/payment/stripe/customer/retrieve/{stripeUserId}")
    public Response<Customer> getCustomer(@PathVariable("stripeUserId") String stripeUserId)
            throws CardException, APIException,
            AuthenticationException, InvalidRequestException, APIConnectionException {
        Customer foundCustomer = stripePaymentServiceImpl.getCustomer(stripeUserId);
        return Response.ok().body(foundCustomer);
    }

    // TODO: Если будет доделан клиент, то необходимо использовать этот метод
    //  и создать отдельный метод charge в StripePaymentServiceImpl!
    @PostMapping("/api/payment/stripe/charge")
    public Response<String> charge(@RequestBody StripePaymentChargeRequestWithToken stripePaymentChargeRequest) throws StripeException {
        if (stripePaymentChargeRequest.getStripeToken().isEmpty()) {
            return Response.error(HttpStatus.BAD_REQUEST, "Stripe payment token is missing. Try again later.");
        }
        Charge charge = stripePaymentServiceImpl.chargeBackendOnly(stripePaymentChargeRequest);
        return Response.accepted().body(charge.getId());
    }

    /**
     * Обработка Stripe Charge только на сервере.
     *
     * @param stripeRequest DTO для создание Payment.
     *                      Без токена. Он будет генерироваться на сервере.
     * @return Возвращается Id удачно проведенной транзакции.
     */
    @PostMapping("/api/payment/stripe/charge/backendonly")
    public Response<String> chargeBackendOnly(@RequestBody StripePaymentChargeRequest stripeRequest)
            throws StripeException {
        Charge charge = stripePaymentServiceImpl.chargeBackendOnly(stripeRequest);
        return Response.accepted().body(charge.getId());
    }
}