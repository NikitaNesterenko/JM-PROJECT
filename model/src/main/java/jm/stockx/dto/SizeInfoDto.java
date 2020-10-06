package jm.stockx.dto;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SizeInfoDto {

    private String lastSale;
    private String lowestAsk;
    private String highestBid;
    private String itemName;
    private String condition;

    public SizeInfoDto(Money lastSale, Money lowestAsk, Money highestBid, String itemName, String condition) {
        this.lastSale = lastSale.toString();
        this.lowestAsk = lowestAsk.toString();
        this.highestBid = highestBid.toString();
        this.itemName = itemName;
        this.condition = condition;
    }
}
