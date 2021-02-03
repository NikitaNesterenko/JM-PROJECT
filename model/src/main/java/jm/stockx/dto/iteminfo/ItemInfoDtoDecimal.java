package jm.stockx.dto.iteminfo;

import jm.stockx.entity.ItemInfo;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoDtoDecimal implements Serializable {

    private Long id;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private BigDecimal price;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private BigDecimal lowestAsk;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private BigDecimal highestBid;

    private String size;

    private Long itemId;

    private String itemImgUrl;

    public ItemInfoDtoDecimal(ItemInfo itemInfo) {
        this.id = itemInfo.getId();
        this.price = itemInfo.getPrice().getAmount();
        this.lowestAsk = itemInfo.getLowestAsk().getAmount();
        this.highestBid = itemInfo.getHighestBid().getAmount();
        this.size = itemInfo.getSize().getSize();
        this.itemId = itemInfo.getItem().getId();
        this.itemImgUrl = itemInfo.getItemImageUrl();
    }

    public ItemInfoDtoDecimal (ItemInfoDto itemInfoDto){
        this.id = itemInfoDto.getId();
        this.price = itemInfoDto.getPrice().getAmount();
        this.lowestAsk = itemInfoDto.getLowestAsk().getAmount();
        this.highestBid = itemInfoDto.getHighestBid().getAmount();
        this.size = itemInfoDto.getSize();
        this.itemId = itemInfoDto.getItemId();
        this.itemImgUrl = itemInfoDto.getItemImgUrl();
    }


}
