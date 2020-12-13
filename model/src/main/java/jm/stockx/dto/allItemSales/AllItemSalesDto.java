package jm.stockx.dto.allItemSales;

import java.time.LocalDateTime;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemSize;
import org.joda.money.Money;

public class AllItemSalesDto {
    private Money price;
    private LocalDateTime dateOfSale;
    private ItemSize size;

    public AllItemSalesDto(BuyingInfo buyingInfo, ItemSize size) {
        this.price = buyingInfo.getBuyingPrice();
        this.dateOfSale = buyingInfo.getBuyingTimeStamp();
        this.size = size;
    }

    public Money getPrice() {
        return this.price;
    }

    public LocalDateTime getDateOfSale() {
        return this.dateOfSale;
    }

    public ItemSize getSize() {
        return this.size;
    }

    public void setPrice(final Money price) {
        this.price = price;
    }

    public void setDateOfSale(final LocalDateTime dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public void setSize(final ItemSize size) {
        this.size = size;
    }

    public AllItemSalesDto() {
    }

    public AllItemSalesDto(final Money price, final LocalDateTime dateOfSale, final ItemSize size) {
        this.price = price;
        this.dateOfSale = dateOfSale;
        this.size = size;
    }

    public String toString() {
        Money var10000 = this.getPrice();
        return "AllItemSalesDto(price=" + var10000 + ", dateOfSale=" + this.getDateOfSale() + ", size=" + this.getSize() + ")";
    }
}
