package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserDto {

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String token;

    @NotNull
    private UserDto userDTO;
}
