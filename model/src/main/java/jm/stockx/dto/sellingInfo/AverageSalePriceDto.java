package jm.stockx.dto.sellingInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageSalePriceDto {

    private Long itemId;
    private Money averagePrice;
}
