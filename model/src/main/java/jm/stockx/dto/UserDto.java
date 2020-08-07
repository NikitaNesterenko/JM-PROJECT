package jm.stockx.dto;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Имя не должно быть null, пустым или состоять из одних лишь пробельных символов")
    private String firstName;

    @NotBlank(message = "Фамилия не должна быть null, пустой или состоять из одних лишь пробельных символов")
    private String lastName;

    @Email(message = "Адрес электронной почты должен быть корректным")
    private String email;

    @NotBlank(message = "Username не должен быть null, пустым или состоять из одних лишь пробельных символов")
    private String username;

    @NotBlank(message = "Пароль не должен быть null, пустым или состоять из одних лишь пробельных символов")
    private String password;

    @Min(1)
    @NotNull
    private byte sellerLevel;

    private boolean vacationMode;

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
