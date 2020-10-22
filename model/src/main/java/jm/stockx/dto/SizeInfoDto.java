package jm.stockx.dto;

import jm.stockx.entity.ItemSize;
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
    private String itemSize;


    public SizeInfoDto(Money lowestAsk, Money highestBid, String itemName, String condition, Money lastSalePrice, ItemSize itemSize) {
        this.lowestAsk = lowestAsk.toString();
        this.highestBid = highestBid.toString();
        this.itemName = itemName;
        this.condition = condition;
        this.lastSalePrice = lastSalePrice.toString();
        this.itemSize = itemSize.getSize();
    }

    public void setLastSalePrice(Money lastSalePrice) {
        this.lastSalePrice = lastSalePrice.toString();
    }

    public void setShoeSize(ItemSize itemSize) {
        this.itemSize = itemSize.toString();
    }
}
