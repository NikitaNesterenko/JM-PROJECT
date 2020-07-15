package jm.stockx.dto;

import jm.stockx.entity.PurchaseInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoPostDto {

    private LocalDateTime purchaseTimeStamp;

    private Long itemId;

    private Double purchasePrice;

    public PurchaseInfoPostDto(PurchaseInfo purchaseInfo) {
        this.purchaseTimeStamp = purchaseInfo.getPurchaseTimeStamp();
        this.itemId = purchaseInfo.getItemId();
        this.purchasePrice = purchaseInfo.getPurchasePrice();
    }

}
