package jm.stockx.paypalintegration;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayPalController {
    private PayPalService payPalService;

    public PayPalController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/api/payment/paypal/get")
    public ResponseEntity<?> createPayment(@RequestBody PayPalOrder orderForProcessing) {
        try {
            Payment payment = payPalService.createPayment(
                    orderForProcessing.getPrice(),
                    orderForProcessing.getCurrency(),
                    orderForProcessing.getMethod(),
                    orderForProcessing.getIntent(),
                    orderForProcessing.getDescription(),
                    orderForProcessing.getCancelURL(),
                    orderForProcessing.getSuccessURL());
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return ResponseEntity.ok(link.getHref());
                }
            }

            return ResponseEntity.noContent().build();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/api/payment/paypal/put")
    public ResponseEntity<?> makePayment(@RequestParam("paymentId") String paymentId,
                                         @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return ResponseEntity.ok(payment.toString());
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}