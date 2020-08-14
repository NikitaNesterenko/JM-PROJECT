package jm.stockx.dto;

import jm.stockx.entity.Item;
import jm.stockx.enums.ItemColors;
import lombok.*;

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
    private Double price;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double retailPrice;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double lowestAsk;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private Double highestBid;

    private LocalDate dateRelease;

    @NotBlank
    private String condition;
    private ItemColors itemColors;

    public ItemDto(@NonNull Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.retailPrice = item.getRetailPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.dateRelease = item.getReleaseDate();
        this.condition = item.getCondition();
        this.itemColors = item.getItemColors();
    }
}
