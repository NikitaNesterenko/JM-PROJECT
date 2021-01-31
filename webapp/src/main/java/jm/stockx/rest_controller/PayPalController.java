package jm.stockx.rest_controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jm.stockx.dto.paypalintegration.PayPalOrderDto;
import jm.stockx.PayPalServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayPalController {
    private final PayPalServiceImpl payPalServiceImpl;

    public PayPalController(PayPalServiceImpl payPalServiceImpl) {
        this.payPalServiceImpl = payPalServiceImpl;
    }

    /**
     * Создается объект Payment, с использованием HATEOAS получается URL для подтверждения транзакции.
     *
     * @param orderForProcessing – Dto, содержащий данные для создания объекта Payment.
     * @return ResponseEntity<String> – содержит адрес для подтверждения пользователем транзакции.
     */
    @PostMapping("/api/payment/paypal/creation")
    public ResponseEntity<String> createPayment(@RequestBody PayPalOrderDto orderForProcessing) {
        try {
            Payment payment = payPalServiceImpl.createPayment(orderForProcessing);
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

    @PostMapping("/api/payment/paypal/execution")
    public ResponseEntity<String> makePayment(@RequestParam("paymentId") String paymentId,
                                              @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = payPalServiceImpl.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {
                return ResponseEntity.ok("Payment approved");
            }
        } catch (PayPalRESTException e) {
            e.getMessage();
        }
        return ResponseEntity.badRequest().build();
    }
}