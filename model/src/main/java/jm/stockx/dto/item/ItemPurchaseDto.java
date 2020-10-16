package jm.stockx.dto.item;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ItemPurchaseDto {

    private String itemCategory;
    private Double count;

    public ItemPurchaseDto(String itemCategory, Double count) {
        this.itemCategory = itemCategory;
        this.count = count;
    }

    public ItemPurchaseDto() {
    }
}
