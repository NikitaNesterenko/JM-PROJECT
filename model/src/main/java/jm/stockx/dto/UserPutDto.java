package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPutDto {

    @NotNull                        // так как put для изменения, id должен быть
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

    public UserPutDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.sellerLevel = user.getSellerLevel();
        this.vacationMode = user.getVacationMode();
    }
}
