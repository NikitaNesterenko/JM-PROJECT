package jm.stockx.dto.itemInfo;

import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ItemSearchDto {
    private String itemCategoryStr;
    private Long count;

    public ItemSearchDto(ItemCategory itemCategory, Long count){
        this.itemCategoryStr = itemCategory.toString();
        this.count = count;
    }

}