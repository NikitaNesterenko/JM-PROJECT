package jm.stockx.dto.userLocaliseInfo;

import jm.stockx.entity.UserLocaliseInfo;
import jm.stockx.enums.UserCountry;
import jm.stockx.enums.UserCurrency;
import jm.stockx.enums.UserLanguage;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLocaliseInfoDto {

    private Long id;

    @NotBlank(message = "User должен где-то жить")
    private UserCountry userCountry;

    @NotBlank(message = "User должен на что-то кушать")
    private UserCurrency userCurrency;

    @NotBlank(message = "У User должен быть язык")
    private UserLanguage userLanguage;

    @NotBlank(message = "User name is mandatory (красиво звучит - мандатори!")
    private String userName;

    public UserLocaliseInfoDto(UserLocaliseInfo userLocaliseInfo) {
//        this.id = userLocaliseInfo.getId();
        this.userCountry = userLocaliseInfo.getUserCountry();
        this.userCurrency = userLocaliseInfo.getUserCurrency();
        this.userLanguage = userLocaliseInfo.getUserLanguage();
        this.userName = userLocaliseInfo.getUser().getUsername();
    }
}
