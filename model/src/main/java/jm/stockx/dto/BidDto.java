package jm.stockx.dto;

import jm.stockx.entity.Bid;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {

        private Long id;

        private Double price;

        private Boolean success;

        public BidDto(@NonNull Bid bid) {
                this.id = bid.getId();
                this.price = bid.getPrice();
                this.success = bid.getSuccess();
        }
}
