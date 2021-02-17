package jm.stockx.dto.currency;

import jm.stockx.entity.Currency;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    private Long id;

    @NotBlank(message = "Имя не должно быть null, пустым или состоять из одних лишь пробельных символов")
    private String name;

    public CurrencyDto(Currency currency) {
        this.id = currency.getId();
        this.name = currency.getName();
    }
}
