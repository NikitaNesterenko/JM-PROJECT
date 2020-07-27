package jm.stockx.dto;

import jm.stockx.entity.Bid;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {

        @Null                                   // автогенерация в БД
        private Long id;

        @Positive                               // значение положительное или null
        @NotNull
        private Double price;

        @NotNull
        private Boolean success;

        public BidDto(@NonNull Bid bid) {
                this.id = bid.getId();
                this.price = bid.getPrice();
                this.success = bid.getSuccess();
        }
}
