package jm.stockx.dto;

import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import jm.stockx.entity.ShoeSize;
import jm.stockx.entity.Style;
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
import java.util.List;

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

    @NotNull
    private String brand;

    private String itemImageUrl;

    private String style;

    private List<String> sizes;

    private String itemColor;

    public ItemDto(@NonNull Item i) {
        this.id = i.getId();
        this.name = i.getName();
        this.price = i.getPrice();
        this.retailPrice = i.getRetailPrice();
        this.lowestAsk = i.getLowestAsk();
        this.highestBid = i.getHighestBid();
        this.dateRelease = i.getDateRelease();
        this.condition = i.getCondition();
        this.condition = i.getDescription();
        this.itemColor = i.getItemColors().toString();
        this.brand = i.getBrand().getName();
        this.itemImageUrl = i.getItemImageUrl();
        this.style = i.getStyle().getName();
        i.getSizes().forEach(x -> this.sizes.add(x.getSize().toString()));
    }


    public ItemDto(Long id,
                   @NotBlank String name,
                   @NotNull Money price,
                   @NotNull Money lowestAsk,
                   @NotNull Money highestBid,
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
        this.itemColor = itemColors.toString();
    }


    public ItemDto(Long id,
                   String name,
                   Money price,
                   Money retailPrice,
                   Money lowestAsk,
                   Money highestBid,
                   LocalDate dateRelease,
                   String condition,
                   String description,
                   Brand brand,
                   String itemImageUrl,
                   Style style,
                   List<ShoeSize> sizes,
                   ItemColors itemColor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.dateRelease = dateRelease;
        this.condition = condition;
        this.description = description;
        this.brand = brand.getName();
        this.itemImageUrl = itemImageUrl;
        this.style = style.getName();
        sizes.forEach(x -> this.sizes.add(x.getSize().toString()));
        this.itemColor = itemColor.toString();
    }
}
