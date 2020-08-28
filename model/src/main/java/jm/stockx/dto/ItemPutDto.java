package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

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
    private BigDecimal price;

    @NotBlank
    private String priceCurrency;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private BigDecimal retailPrice;

    @NotBlank
    private String retailPriceCurrency;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private BigDecimal lowestAsk;

    @NotBlank
    private String lowestAskCurrency;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private BigDecimal highestBid;

    @NotBlank
    private String highestBidCurrency;

    @NotBlank
    private String condition;

    private String description;

    public ItemPutDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice().getAmount();
        this.priceCurrency = item.getPrice().getCurrencyUnit().toString();
        this.retailPrice = item.getRetailPrice().getAmount();
        this.retailPriceCurrency = item.getRetailPrice().getCurrencyUnit().toString();
        this.lowestAsk = item.getLowestAsk().getAmount();
        this.lowestAskCurrency = item.getLowestAsk().getCurrencyUnit().toString();
        this.highestBid = item.getHighestBid().getAmount();
        this.highestBidCurrency = item.getHighestBid().getCurrencyUnit().toString();
        this.condition = item.getCondition();
        this.description = item.getDescription();
    }
}
