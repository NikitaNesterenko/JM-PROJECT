package jm.stockx.dto.currency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

}
