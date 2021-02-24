package jm.stockx.dto.allitemsales;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemSize;
import lombok.*;
import org.joda.money.Money;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllItemSalesDto {

    private Money price;
    private LocalDateTime dateOfSale;
    private ItemSize size;

    public AllItemSalesDto(BuyingInfo buyingInfo, ItemSize size) {
        this.price = buyingInfo.getBuyingPrice();
        this.dateOfSale = buyingInfo.getBuyingTimeStamp();
        this.size = size;
    }


}
