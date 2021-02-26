package jm.stockx.util.striperequest;

import lombok.*;

/**
 * Создание Stripe Payment с получением токена от клиента.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StripePaymentChargeRequestWithToken extends StripePaymentChargeRequest {
    private String stripeToken;
}