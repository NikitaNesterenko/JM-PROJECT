package jm.stockx.dto.userLocaliseInfo;

import jm.stockx.enums.UserCountry;
import jm.stockx.enums.UserCurrency;
import jm.stockx.enums.UserLanguage;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLocaliseInfoPostDto {

    private Long id;

    private UserCountry userCountry;

    private UserCurrency userCurrency;

    private UserLanguage userLanguage;

    @NonNull
    private Long userId;
}
