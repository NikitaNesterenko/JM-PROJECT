package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private Double price;
    private Double lowestAsk;
    private Double highestBid;
    private LocalDate dateRelease;
    private String condition;

    public ItemDto(@NonNull Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.dateRelease = item.getDateRelease();
        this.condition = item.getCondition();
    }
}
