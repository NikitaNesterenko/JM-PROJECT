package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @Email
    private String email;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String password;

    private Boolean rememberMe;

    public UserLoginDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserLoginDto(UserDto userDTO) {
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }
}
