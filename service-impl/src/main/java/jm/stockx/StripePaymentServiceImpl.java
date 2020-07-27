package jm.stockx;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import jm.stockx.util.PaymentChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentServiceImpl{

    @Value("${STRIPE_SECRET_KEY}")
    String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(PaymentChargeRequest paymentChargeRequest)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException, com.stripe.exception.AuthenticationException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentChargeRequest.getAmount());
        chargeParams.put("currency", paymentChargeRequest.getCurrency());
        chargeParams.put("description", paymentChargeRequest.getDescription());
        chargeParams.put("source", paymentChargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}
