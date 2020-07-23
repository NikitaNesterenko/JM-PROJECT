package jm.stockx.dto;

import jm.stockx.entity.Currency;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPutDto {

    private long id;
    private String name;

    public CurrencyPutDto(Currency currency) {
        this.id = currency.getId();
        this.name = currency.getName();
    }
}
