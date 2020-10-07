package jm.stockx.dto;

import jm.stockx.enums.ItemDirection;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemSearchListDto {
    private Map<ItemDirection, Integer> countItem;

    public ItemSearchListDto() {
        this.countItem = new HashMap<>();
    }

    public String toString(ItemDirection itemDirection) {
        return countItem.get(itemDirection).toString();
    }
}
