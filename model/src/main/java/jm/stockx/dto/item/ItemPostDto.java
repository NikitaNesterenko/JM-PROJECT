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
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPostDto {

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

    @NotNull
    private LocalDate dateRelease;

    @NotBlank
    private String condition;

    @NotBlank
    private String description;

    public ItemPostDto(Item item, ItemInfo itemInfo) {
        this.price = itemInfo.getPrice();
        this.retailPrice = itemInfo.getPrice();
        this.lowestAsk = itemInfo.getLowestAsk();
        this.highestBid = itemInfo.getHighestBid();
        this.dateRelease = itemInfo.getReleaseDate();
        this.condition = itemInfo.getCondition();
        this.condition = itemInfo.getDescription();
    }

    public ItemPostDto(ItemInfo itemInfo) {
        this.retailPrice = itemInfo.getPrice();
        this.dateRelease = itemInfo.getReleaseDate();
        this.condition = itemInfo.getCondition();
        this.condition = itemInfo.getDescription();
    }
}
