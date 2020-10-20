package jm.stockx.dto.itemInfo;

import jm.stockx.enums.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemSearchDto {
    private ItemCategory itemCategory;
    private Long count;

}