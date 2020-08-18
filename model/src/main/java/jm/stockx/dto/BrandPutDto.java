package jm.stockx.dto;

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
public class BrandPutDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Добавьте название бренда. Название не может быть пустым или состоять из одних пробелов")
    private String name;
}
