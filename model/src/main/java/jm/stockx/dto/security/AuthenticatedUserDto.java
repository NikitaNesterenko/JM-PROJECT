package jm.stockx.dto.security;

import jm.stockx.dto.user.UserDto;
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
public class AuthenticatedUserDto {

    @NotBlank
    private String token;

    @NotNull
    private UserDto userDTO;
}
