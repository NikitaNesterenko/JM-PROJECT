package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.PaymentInfo;
import jm.stockx.enums.Status;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingInfoDto {

    private Long id;

    @NotNull
    private LocalDateTime buyingTimeStamp;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double buyingPrice;

    private Set<Item> boughtItems;

    private Set<PaymentInfo> paymentsInfo;

    @NotNull
    private Status status;

    public BuyingInfoDto(LocalDateTime buyingTimeStamp,
                         Double buyingPrice,
                         Set<Item> boughtItems,
                         Set<PaymentInfo> paymentsInfo,
                         Status status) {
        this.buyingTimeStamp = buyingTimeStamp;
        this.buyingPrice = buyingPrice;
        this.boughtItems = boughtItems;
        this.paymentsInfo = paymentsInfo;
        this.status = status;
    }

    public BuyingInfoDto(BuyingInfo buyingInfo) {
        this.id = buyingInfo.getId();
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
        this.boughtItems = buyingInfo.getBoughtItems();
        this.paymentsInfo = buyingInfo.getPaymentsInfo();
        this.status = buyingInfo.getStatus();
    }
}
