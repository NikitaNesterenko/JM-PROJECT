package jm.stockx.dto;

import jm.stockx.entity.Bid;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidPostDto {

    @Positive                               // значение положительное или null
    @NotNull
    private Double price;

    private Boolean success;

    public BidPostDto(Bid bid) {
        this.price = bid.getPrice();
        this.success = bid.getSuccess();
    }
}
