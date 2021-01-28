package jm.stockx.paypalintegration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayPalOrder {
    private Double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String cancelURL;
    private String successURL;
}
