package jm.stockx.dto;

import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoGetDto {

    private String ItemName;

    private String itemImageUrl;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money lowestAsk;

    public ItemInfoGetDto(Item item) {
        this.ItemName = item.getName();
        this.itemImageUrl = item.getItemImageUrl();
        this.lowestAsk = item.getItemInfo().getLowestAsk();
    }
}
