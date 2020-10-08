package jm.stockx.dto.itemInfo;

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
public class ItemInfoCardDto {

    private String ItemName;

    private String itemImageUrl;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money lowestAsk;

    public ItemInfoCardDto(ItemInfo itemInfo) {
        this.ItemName = itemInfo.getItem().getName();
        this.itemImageUrl = itemInfo.getItem().getItemImageUrl();
        this.lowestAsk = itemInfo.getLowestAsk();
    }
}
