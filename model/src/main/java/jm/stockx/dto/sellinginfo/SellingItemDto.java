package jm.stockx.dto.sellinginfo;

import jm.stockx.entity.Item;
import lombok.*;
import org.joda.money.Money;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellingItemDto {
    private Item item;
    private LocalDateTime orderDate;
    private Money price;

}
