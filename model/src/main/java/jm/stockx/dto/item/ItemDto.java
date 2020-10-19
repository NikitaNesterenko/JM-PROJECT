package jm.stockx.dto.item;

import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
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

    public ItemDto(@NonNull ItemInfo itemInfo) {
        this.id = itemInfo.getItem().getId();
        this.name =itemInfo.getItem().getName();
        this.retailPrice = itemInfo.getPrice();
        this.dateRelease = itemInfo.getReleaseDate();
        this.condition =   itemInfo.getCondition();
        this.description = itemInfo.getDescription();
        this.itemColors =  itemInfo.getItemColors();
    }

    public ItemDto(Item item) {
    }
}
