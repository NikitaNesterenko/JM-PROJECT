package jm.stockx.dto.itemInfo;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InfoTickerDto {
    private String splitItemName;
    private Money price;
    private boolean flagDiffPrice;
}
