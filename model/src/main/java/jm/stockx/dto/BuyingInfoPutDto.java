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
public class BuyingInfoPutDto {

    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime buyingTimeStamp;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double buyingPrice;

    public BuyingInfoPutDto(BuyingInfo buyingInfo) {
        this.id = buyingInfo.getId();
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
    }

}
