package jm.stockx.dto.sellingInfo;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPriceChangeDto {
    private String diffPercent;
    private Money diffMoney;

}
