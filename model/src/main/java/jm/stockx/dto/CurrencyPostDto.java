package jm.stockx.dto;

import jm.stockx.entity.Currency;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPostDto {

    private String name;

    public CurrencyPostDto(Currency currency) {
        this.name = currency.getName();
    }
}
