package jm.stockx.dto.sellingInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageSalePriceDto {

    private Long itemId;
    private Money averagePrice;
    private Money currentPrice;
    private Double differenceInPrice;

    public AverageSalePriceDto(Long itemId, Money averagePrice, Money currentPrice) {
        this.itemId = itemId;
        this.averagePrice = averagePrice;
        this.currentPrice = currentPrice;
    }

    public double calculatePercentDifferenceInPrice(){
        double current = currentPrice.getAmount().doubleValue();
        double average = averagePrice.getAmount().doubleValue();
        return (average - current) / current * 100;
    }
}






