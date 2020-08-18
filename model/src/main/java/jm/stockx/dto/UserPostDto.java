package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {

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
    private String appleUserId;

    public UserPostDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.sellerLevel = user.getSellerLevel();
        this.vacationMode = user.getVacationMode();
        this.appleUserId = user.getAppleUserId();
    }
}
