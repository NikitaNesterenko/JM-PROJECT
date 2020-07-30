package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandPostDto {

    @NotBlank(message = "Добавьте название бренда. Название не может быть пустым или состоять из одних пробелов")
    private String name;
}
