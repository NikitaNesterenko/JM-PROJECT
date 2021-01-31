package jm.stockx.dto.paypalintegration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayPalOrderDto {
    private Double price;
    private String currency;
    private String description;
}