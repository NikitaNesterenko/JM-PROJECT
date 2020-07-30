package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingInfoDto {

    private Long id;
    private LocalDateTime buyingTimeStamp;
    private Double buyingPrice;

    public BuyingInfoDto(BuyingInfo buyingInfo) {
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
    }
}
