package jm.stockx.dto.brand;

import jm.stockx.entity.Brand;
import lombok.*;

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

    public BrandDto(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }
}