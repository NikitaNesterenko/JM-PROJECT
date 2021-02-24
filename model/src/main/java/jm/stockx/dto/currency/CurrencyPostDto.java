package jm.stockx.dto.currency;

import jm.stockx.entity.Currency;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPostDto {

    @NotBlank
    private String name;

    public CurrencyPostDto(Currency currency) {
        this.name = currency.getName();
    }
}
