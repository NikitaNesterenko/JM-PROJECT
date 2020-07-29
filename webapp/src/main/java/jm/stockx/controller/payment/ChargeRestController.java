package jm.stockx.controller.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jm.stockx.StripePaymentServiceImpl;
import jm.stockx.util.PaymentChargeRequest;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class ChargeRestController {

    private final StripePaymentServiceImpl stripePaymentServiceImpl;

    @Autowired
    public ChargeRestController(StripePaymentServiceImpl stripePaymentServiceImpl) {
        this.stripePaymentServiceImpl = stripePaymentServiceImpl;
    }

    @PostMapping("/payment/charge")
    public Response<Boolean> charge(PaymentChargeRequest paymentChargeRequest, Model model, String currency) throws StripeException, AuthenticationException {

        if (paymentChargeRequest.getStripeToken() == null) {
            return Response.error(HttpStatus.BAD_REQUEST, "Stripe token is null!");
        }
        paymentChargeRequest.setDescription("JM charge");
        paymentChargeRequest.setCurrency(PaymentChargeRequest.Currency.valueOf(currency));
        Charge charge = stripePaymentServiceImpl.charge(paymentChargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return Response.ok().build();
    }
}
