package jm.stockx.util.striperequest;

import lombok.*;

/**
 * Создание Stripe Payment без получения токена от клиента.
 *
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StripePaymentChargeRequest {
    private String description;
    /**
     * Указывается в центах. 9.99 USD = 999
     */
    private int amount;
    private StripeCurrencyEnum currency;
    private String stripeEmail;
}