package jm.stockx.dto.itemInfo;

import jm.stockx.entity.ItemInfo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoImageDto {

    private Long itemId;

    private String itemName;

    private String itemImageUrl;

    public ItemInfoImageDto(ItemInfo itemInfo) {
        this.itemId = itemInfo.getItem().getId();
        this.itemName = itemInfo.getItem().getName();
        this.itemImageUrl = itemInfo.getItemImageUrl();
    }
}
