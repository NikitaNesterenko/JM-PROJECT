package jm.stockx.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayPalConfig {
    /**
     * Параметры считываются из property файла:
     * mode – указывает на режим проведения транзакций;
     * clientId – идентификатор приложения;
     * clientSecret – секрет приложения.
     */
    @Value("${paypal.mode}")
    private String mode;

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    /**
     * Создается с Map параметрами конфигурации SDK для дальнейшего переиспользования.
     *
     * @return Map<String, String>
     */
    @Bean
    public Map<String, String> payPalSDKConfigParams() {
        Map<String, String> configParams = new HashMap<>();
        configParams.put("mode", mode);
        return configParams;
    }

    /**
     * Создается OAuthTokenCredential для проверки при проведении транзакции.
     *
     * @return OAuthTokenCredential
     */
    @Bean
    public OAuthTokenCredential payPalOAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, payPalSDKConfigParams());
    }

    /**
     * Создается контекст для отправки OAuthTokenCredential токена и настроек.
     *
     * @return APIContext
     */
    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(payPalOAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(payPalSDKConfigParams());
        return context;
    }
}