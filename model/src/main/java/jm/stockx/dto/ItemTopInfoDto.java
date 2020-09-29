package jm.stockx.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
