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
public class ItemInfoDto {

    private Long id;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money price;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money lowestAsk;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private Money highestBid;

    private String size;

    private Long itemId;

    private String itemImgUrl;

    public ItemInfoDto(ItemInfo itemInfo) {
        this.id = itemInfo.getId();
        this.price = itemInfo.getPrice();
        this.lowestAsk = itemInfo.getLowestAsk();
        this.highestBid = itemInfo.getHighestBid();
        this.size = itemInfo.getSize().getSize();
        this.itemId = itemInfo.getItem().getId();
        this.itemImgUrl = itemInfo.getItemImageUrl();
    }

}
