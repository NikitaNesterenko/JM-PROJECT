package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "{data.firstName}")
    private String firstName;

    @NotBlank(message = "{data.lastName}")
    private String lastName;

    @Email(message = "{data.email}")
    private String email;

    @NotBlank(message = "{data.firstName}")
    private String username;

    @NotBlank(message = "{data.password}")
    private String password;

    @Min(1)
    @NotNull
    private byte sellerLevel;

    private boolean vacationMode;

    @NotBlank
    private String localeTag;

    private Set<BuyingInfo> buyingInfo;

    private String appleUserId;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.sellerLevel = user.getSellerLevel();
        this.buyingInfo = user.getBuyingInfo();
        this.vacationMode = user.getVacationMode();
        this.localeTag = user.getLocaleTag();
    }

    public UserDto(String firstName,
                   String lastName,
                   String email,
                   String username,
                   String password,
                   Byte sellerLevel,
                   Boolean vacationMode,
                   String localeTag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
        this.vacationMode = vacationMode;
        this.localeTag = localeTag;
    }
}
