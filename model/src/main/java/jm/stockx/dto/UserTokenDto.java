package jm.stockx.dto;

import jm.stockx.entity.User;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

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
    private String roleName;

    public UserTokenDto(String token) {
        User principal = getPrincipal();
        this.id = principal.getId();
        this.email = principal.getEmail();
        this.firstName = principal.getFirstName();
        this.lastName = principal.getLastName();
        this.token = token;
        this.roleName = principal.getRole().getRoleName();
    }

    private User getPrincipal() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}