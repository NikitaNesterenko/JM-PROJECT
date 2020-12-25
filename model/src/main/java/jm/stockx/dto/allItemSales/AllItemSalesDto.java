package jm.stockx.dto.allItemSales;

import java.time.LocalDateTime;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemSize;
import lombok.*;
import org.joda.money.Money;

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
