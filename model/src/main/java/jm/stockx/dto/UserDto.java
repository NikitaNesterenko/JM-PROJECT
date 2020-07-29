package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Null
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email(message = "Адрес электронной почты должен быть корректным")
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Min(1)
    @NotNull
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
