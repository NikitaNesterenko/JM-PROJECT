package jm.stockx.dto;

import jm.stockx.entity.Item;
import jm.stockx.entity.ShoeSize;
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
    private Money retailPrice;

    private LocalDate dateRelease;

    @NotBlank
    private String condition;

    @NotBlank
    private String description;

    private ItemColors itemColors;

    private ShoeSize shoeSize;

    public ItemDto(@NonNull Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.retailPrice = item.getRetailPrice();
        this.dateRelease = item.getReleaseDate();
        this.condition = item.getCondition();
        this.description = item.getDescription();
        this.itemColors = item.getItemColors();
        this.shoeSize = item.getShoeSize();
    }
}
