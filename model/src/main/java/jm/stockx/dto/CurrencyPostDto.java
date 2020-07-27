package jm.stockx.dto;

import jm.stockx.entity.Currency;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPostDto {

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String name;

    public CurrencyPostDto(Currency currency) {
        this.name = currency.getName();
    }
}
