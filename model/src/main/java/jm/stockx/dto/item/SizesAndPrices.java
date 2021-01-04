package jm.stockx.dto.item;

import lombok.*;
import org.joda.money.Money;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SizesAndPrices {

    private String sizeName;
    private Money lastSale;
    private Money lowestAsk;
    private Money highestBid;

}
