package jm.stockx.dto;

import jm.stockx.entity.Role;
import jm.stockx.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private String role;

    public UserTokenDto(User user, String token) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.token = token;
        this.role = user.getRole().getRoleName();
    }
}
