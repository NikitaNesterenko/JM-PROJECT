package jm.stockx.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemPriceChartDto {

    private String brandName;

    private String modelName;

    private BigDecimal[] prices;

}
