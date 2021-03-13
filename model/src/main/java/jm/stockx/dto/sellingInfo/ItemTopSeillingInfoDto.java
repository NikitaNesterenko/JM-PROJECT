package jm.stockx.dto.sellingInfo;

import lombok.*;
import org.joda.money.Money;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemTopSeillingInfoDto {
        private Long id;
        private String name;
        private Long saleCount;
}
