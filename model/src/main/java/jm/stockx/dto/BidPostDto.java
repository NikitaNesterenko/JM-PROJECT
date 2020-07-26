package jm.stockx.dto;

import jm.stockx.entity.Bid;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidPostDto {

    private Double price;
    private Boolean success;

    public BidPostDto(Bid bid) {
        this.price = bid.getPrice();
        this.success = bid.getSuccess();
    }
}
