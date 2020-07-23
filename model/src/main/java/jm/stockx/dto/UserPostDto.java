package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private byte sellerLevel;
    private boolean vacationMode;

    public UserPostDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.sellerLevel = user.getSellerLevel();
        this.vacationMode = user.getVacationMode();
    }
}
