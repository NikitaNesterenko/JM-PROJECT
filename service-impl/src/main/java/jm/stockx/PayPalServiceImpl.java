package jm.stockx;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jm.stockx.dto.paypalintegration.PayPalOrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayPalServiceImpl {
    @Value("${paypal.client.method}")
    private String method;

    @Value("${paypal.client.intent}")
    private String intent;

    @Value("${paypal.client.cancelURL}")
    private String cancelURL;

    @Value("${paypal.client.successURL}")
    private String successURL;

    private final APIContext context;

    public PayPalServiceImpl(APIContext context) {
        this.context = context;
    }

    /**
     * Создается объект Payment, который будет использоваться для передачи.
     *
     * @param orderForProcessing
     * @return Payment
     * @throws PayPalRESTException
     */
    public Payment createPayment(PayPalOrderDto orderForProcessing) throws PayPalRESTException {
        Amount amount = createAmount(orderForProcessing);
        List<Transaction> transactions = createTransactionsList(orderForProcessing, amount);
        Payer payer = createPayer();
        RedirectUrls redirectUrls = createRedirectUrls();

        Payment payment = createPayment(transactions, payer);
        payment.setRedirectUrls(redirectUrls);
        return payment.create(context);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(context, paymentExecution);
    }

    private Amount createAmount(PayPalOrderDto orderForProcessing) {
        Amount amount = new Amount();
        amount.setCurrency(orderForProcessing.getCurrency());
        Double priceForOrder = convertToRightDouble(orderForProcessing);
        amount.setTotal(String.format("%.2f", priceForOrder).replace(",", "."));
        return amount;
    }

    private Double convertToRightDouble(PayPalOrderDto orderForProcessing) {
        return BigDecimal.valueOf(orderForProcessing.getPrice())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private List<Transaction> createTransactionsList(PayPalOrderDto orderForProcessing, Amount amount) {
        Transaction transaction = new Transaction();
        transaction.setDescription(orderForProcessing.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        return transactions;
    }

    private Payer createPayer() {
        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        return payer;
    }

    private Payment createPayment(List<Transaction> transactions, Payer payer) {
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        return payment;
    }

    private RedirectUrls createRedirectUrls() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelURL);
        redirectUrls.setReturnUrl(successURL);
        return redirectUrls;
    }
}