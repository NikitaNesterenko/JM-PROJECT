package jm.stockx.dto.buyinginfo;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.PaymentInfo;
import jm.stockx.enums.Status;
import lombok.*;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Set;

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
    private Money buyingPrice;

    private Set<ItemInfo> boughtItems;

    private Set<PaymentInfo> paymentsInfo;

    @NotNull
    private Status status;

    public BuyingInfoPutDto(BuyingInfo buyingInfo) {
        this.id = buyingInfo.getId();
        this.buyingTimeStamp = buyingInfo.getBuyingTimeStamp();
        this.buyingPrice = buyingInfo.getBuyingPrice();
        this.boughtItems = buyingInfo.getBoughtItemsInfo();
        this.paymentsInfo = buyingInfo.getPaymentsInfo();
        this.status = buyingInfo.getStatus();
    }
}