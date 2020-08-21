package jm.stockx.dto;

import jm.stockx.entity.Item;
import jm.stockx.enums.ItemColors;
import org.joda.money.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    @NotBlank
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

    private LocalDate dateRelease;

    @NotBlank
    private String condition;

    @NotBlank
    private String description;

    private ItemColors itemColors;

    @Positive
    @NotNull
    private Money lastSalePrice;

    public ItemDto(@NonNull Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.retailPrice = item.getRetailPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.dateRelease = item.getReleaseDate();
        this.condition = item.getCondition();
        this.condition = item.getDescription();
        this.itemColors = item.getItemColors();
        this.lastSalePrice = item.getLastSalePrice();
    }

    public ItemDto(
            Long id,
            @NotBlank String name,
            @Positive(message = "Цена должна быть положительной") @NotNull Money price,
            @Positive(message = "Цена должна быть положительной") @NotNull Money lowestAsk,
            @Positive(message = "Ставка должна быть положительной") @NotNull Money highestBid,
            LocalDate dateRelease,
            @NotBlank String condition,
            ItemColors itemColors) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.dateRelease = dateRelease;
        this.condition = condition;
        this.itemColors = itemColors;
    }
}
