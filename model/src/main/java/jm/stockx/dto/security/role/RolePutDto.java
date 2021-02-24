package jm.stockx.dto.security.role;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RolePutDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Добавьте название роли. Название не может быть пустым или состоять из одних пробелов")
    private String name;
}
