package jm.stockx.dto.brand;

import jm.stockx.entity.Brand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    private Long id;

    @NotBlank(message = "Добавьте название бренда. Название не может быть пустым или состоять из одних пробелов")
    private String name;

    private String logoImage;
}
