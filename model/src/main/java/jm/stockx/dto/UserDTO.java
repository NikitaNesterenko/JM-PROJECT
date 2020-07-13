package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

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
        this.vacationMode = user.isVacationMode();
    }

    public UserDTO(String firstName,
                   String lastName,
                   String email,
                   String username,
                   String password,
                   byte sellerLevel,
                   boolean vacationMode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
        this.vacationMode = vacationMode;
    }
}
