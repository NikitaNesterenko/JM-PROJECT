package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingInfoPostDto {

    private LocalDateTime buyingTimeStamp;
    private Double buyingPrice;

    public BuyingInfoPostDto(BuyingInfo buyingInfo) {
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
    }

}
