package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    @Null
    private Long id;

    @NotBlank(message = "Добавьте название бренда. Название не может быть пустым или состоять из одних пробелов")
    private String name;
}
