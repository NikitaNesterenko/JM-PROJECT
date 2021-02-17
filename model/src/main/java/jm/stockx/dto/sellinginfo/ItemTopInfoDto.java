package jm.stockx.dto.sellinginfo;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemTopInfoDto {
        private Long id;
        private String name;
        private String itemImageUrl;
        private Money lowestAsk;
        private Long count;
}
