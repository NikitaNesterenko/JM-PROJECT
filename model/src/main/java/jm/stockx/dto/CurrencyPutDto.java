package jm.stockx.dto;

import jm.stockx.entity.Currency;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPutDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Имя не должно быть null, пустым или состоять из одних лишь пробельных символов")
    private String name;

    public CurrencyPutDto(Currency currency) {
        this.id = currency.getId();
        this.name = currency.getName();
    }
}
