package jm.stockx.dto.item;

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
