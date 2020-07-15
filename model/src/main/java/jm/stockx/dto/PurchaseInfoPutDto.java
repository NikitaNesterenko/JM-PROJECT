package jm.stockx.dto;

import jm.stockx.entity.PurchaseInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoPutDto {

    private Long id;

    private LocalDateTime purchaseTimeStamp;

    private Long itemId;

    private Double purchasePrice;

    public PurchaseInfoPutDto(PurchaseInfo purchaseInfo) {
        this.id = purchaseInfo.getId();
        this.purchaseTimeStamp = purchaseInfo.getPurchaseTimeStamp();
        this.itemId = purchaseInfo.getItemId();
        this.purchasePrice = purchaseInfo.getPurchasePrice();
    }

}
