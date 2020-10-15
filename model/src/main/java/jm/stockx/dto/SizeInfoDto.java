package jm.stockx.dto;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SizeInfoDto {

    private String lastSalePrice;
    private String lowestAsk;
    private String highestBid;
    private String itemName;
    private String condition;


    public SizeInfoDto(Money lowestAsk, Money highestBid, String itemName, String condition, Money lastSalePrice) {
        this.lowestAsk = lowestAsk.toString();
        this.highestBid = highestBid.toString();
        this.itemName = itemName;
        this.condition = condition;
        this.lastSalePrice = lastSalePrice.toString();
    }

    public SizeInfoDto(String itemName, String condition, Money lowestAsk, Money highestBid) {
        this.lowestAsk = lowestAsk.toString();
        this.highestBid = highestBid.toString();
        this.condition = condition;
        this.itemName = itemName;
    }

    public void setLastSalePrice(Money lastSalePrice) {
        this.lastSalePrice = lastSalePrice.toString();
    }
}
