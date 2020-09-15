package jm.stockx.components.release_calendar;

import jm.stockx.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.money.Money;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ItemHolder {
    Item item;
    String itemName;
    String itemCondition;
    String itemImgUrl;
    Money itemLowestAsk;
    Money itemHighestBid;
    LocalDate itemReleaseDate;

    public ItemHolder(Item item) {
        initItemData(item);
    }

    public void setItem(Item item) {
        initItemData(item);
    }

    private void initItemData(Item item) {
        this.item = item;
        itemName = item.getName();
        itemCondition = item.getCondition();
        itemImgUrl = item.getItemImageUrl();
        itemLowestAsk = item.getLowestAsk();
        itemHighestBid = item.getHighestBid();
        itemReleaseDate = item.getReleaseDate();
    }

}
