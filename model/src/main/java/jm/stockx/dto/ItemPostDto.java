package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPostDto {

    private Double price;
    private Double lowestAsk;
    private Double highestBid;
    private LocalDate dateRelease;
    private String condition;

    public ItemPostDto(Item item) {
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.dateRelease = item.getDateRelease();
        this.condition = item.getCondition();
    }
}
