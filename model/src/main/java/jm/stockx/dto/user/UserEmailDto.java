package jm.stockx.dto.user;

import jm.stockx.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserEmailDto {

    @Email(message = "{data.email}")
    private String email;

    public UserEmailDto(User user) {
        this.email = user.getEmail();
    }

    public UserEmailDto(String email) {
        this.email = email;
    }
}
