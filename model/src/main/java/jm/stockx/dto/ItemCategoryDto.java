package jm.stockx.dto;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDto {
    private String itemName;
    private String imageUrl;
    private Money lowestAsk;
}
