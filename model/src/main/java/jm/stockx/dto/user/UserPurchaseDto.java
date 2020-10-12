package jm.stockx.dto.user;

import jm.stockx.enums.ItemCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserPurchaseDto {

    private ItemCategory itemCategory;

    private Long count;

    public UserPurchaseDto(ItemCategory itemCategory, Long count) {
        this.itemCategory = itemCategory;
        this.count = count;
    }
}
