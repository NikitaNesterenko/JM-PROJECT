package jm.stockx.controller.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jm.stockx.StripePaymentServiceImpl;
import jm.stockx.util.PaymentChargeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;

@Controller
public class ChargeController {

    @Autowired
    StripePaymentServiceImpl stripePaymentServiceImpl;

    @PostMapping("/payment/charge")
    public String charge(PaymentChargeRequest paymentChargeRequest, Model model) throws StripeException, AuthenticationException {
        paymentChargeRequest.setDescription("JM charge");
        paymentChargeRequest.setCurrency(PaymentChargeRequest.Currency.EUR);
        Charge charge = stripePaymentServiceImpl.charge(paymentChargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "paymentResult";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
