package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @Size(min = 1, max = 50, message = "first name must be between 1 and 50 characters long")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Size(min = 1, max = 50, message = "last name must be between 1 and 50 characters long")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @Email(message = "error in email - email format validation failed: ${validatedValue}")
    @NotEmpty(message = "Please provide your email")
    private String email;

    private String username;

    @Size(min = 8, message = "At least 8 characters")
    @NotEmpty(message = "Please provide your password")
    private String password;

    private byte sellerLevel;

    private boolean vacationMode;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.sellerLevel = user.getSellerLevel();
        this.vacationMode = user.getVacationMode();
    }

    public UserDTO(String firstName,
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
