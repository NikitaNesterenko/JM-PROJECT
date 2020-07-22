package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPutDto {

    private Long id;
    private Double price;
    private Double lowestAsk;
    private Double highestBid;
    private String condition;

    public ItemPutDto(Item item) {
        this.id = item.getId();
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.condition = item.getCondition();
    }
}
