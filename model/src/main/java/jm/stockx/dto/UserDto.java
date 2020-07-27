package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Null                           // автогенерация в БД
    private Long id;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String firstName;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String lastName;

    @Email
    private String email;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String username;

    @NotBlank                       // не должно быть null, пустым или состоять из одних лишь пробельных символов
    private String password;

    private byte sellerLevel;

    private boolean vacationMode;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.sellerLevel = user.getSellerLevel();
        this.vacationMode = user.getVacationMode();
    }

    public UserDto(String firstName,
                   String lastName,
                   String email,
                   String username,
                   String password,
                   Byte sellerLevel,
                   Boolean vacationMode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
        this.vacationMode = vacationMode;
    }
}
