package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.PaymentInfo;
import jm.stockx.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

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

    private Set<Item> boughtItems;

    private Set<PaymentInfo> paymentsInfo;

    @NotNull
    private Status status;

    public BuyingInfoPostDto(BuyingInfo buyingInfo) {
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
        this.boughtItems = buyingInfo.getBoughtItems();
        this.paymentsInfo = buyingInfo.getPaymentsInfo();
        this.status = buyingInfo.getStatus();
    }
}
