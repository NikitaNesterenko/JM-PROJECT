package jm.stockx.dto.item;

import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.joda.money.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPutDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Добавьте название товара. Название не может быть пустым или состоять из одних пробелов")
    private String name;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money price;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money retailPrice;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Money lowestAsk;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private Money highestBid;

    @NotBlank
    private String condition;

    private String description;

    public ItemPutDto(Item item, ItemInfo itemInfo) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = itemInfo.getPrice();
        this.retailPrice = itemInfo.getPrice();
        this.lowestAsk = itemInfo.getLowestAsk();
        this.highestBid = itemInfo.getHighestBid();
        this.condition = itemInfo.getCondition();
        this.condition = itemInfo.getDescription();
    }

    public ItemPutDto(ItemInfo itemInfo) {
        this.id = itemInfo.getItem().getId();
        this.name = itemInfo.getItem().getName();
        this.retailPrice = itemInfo.getPrice();
        this.condition = itemInfo.getCondition();
        this.condition = itemInfo.getDescription();
    }
}
