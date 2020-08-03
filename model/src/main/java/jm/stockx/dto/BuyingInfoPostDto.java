package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingInfoPostDto {

    @NotNull
    private LocalDateTime buyingTimeStamp;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double buyingPrice;

    public BuyingInfoPostDto(BuyingInfo buyingInfo) {
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
    }

}
