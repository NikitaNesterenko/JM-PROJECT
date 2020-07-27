package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    @Null                           // автогенерация в БД
    private Long id;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String name;

    @Positive                       // значение положительное или null
    @NotNull
    private Double price;

    @Positive                       // значение положительное или null
    @NotNull
    private Double lowestAsk;

    @Positive                       // значение положительное или null
    @NotNull
    private Double highestBid;

    private LocalDate dateRelease;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String condition;

    public ItemDto(@NonNull Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.dateRelease = item.getReleaseDate();
        this.condition = item.getCondition();
    }
}
