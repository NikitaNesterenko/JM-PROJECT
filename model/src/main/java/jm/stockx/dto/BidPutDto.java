package jm.stockx.dto;

import jm.stockx.entity.Bid;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidPutDto {

    private Long id;

    private Boolean success;

    public BidPutDto(Bid bid) {
        this.id = bid.getId();
        this.success = bid.getSuccess();
    }
}
