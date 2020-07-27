package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPutDto {

    @NotNull                        // так как put для изменения, id должен быть
    private Long id;

    @Positive                       // значение положительное или null
    @NotNull
    private Double price;

    @Positive                       // значение положительное или null
    @NotNull
    private Double lowestAsk;

    @Positive                       // значение положительное или null
    @NotNull
    private Double highestBid;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String condition;

    public ItemPutDto(Item item) {
        this.id = item.getId();
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.condition = item.getCondition();
    }
}
