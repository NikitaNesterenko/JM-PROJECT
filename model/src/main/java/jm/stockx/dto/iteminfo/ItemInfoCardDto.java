package jm.stockx.dto.iteminfo;

import jm.stockx.entity.ItemInfo;
import lombok.*;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoCardDto {

    private String itemName;

    private String itemImageUrl;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money lowestAsk;

    public ItemInfoCardDto(ItemInfo itemInfo) {
        this.itemName = itemInfo.getItem().getName();
        this.itemImageUrl = itemInfo.getItemImageUrl();
        this.lowestAsk = itemInfo.getLowestAsk();
    }
}
