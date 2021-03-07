package jm.stockx.initializer.initmodels;

import jm.stockx.UserLocaliseInfoService;
import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoPostDto;
import jm.stockx.enums.UserCountry;
import jm.stockx.enums.UserCurrency;
import jm.stockx.enums.UserLanguage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания локали пользователя в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class UserLocaliseInfoInit {
    private final UserLocaliseInfoService userLocaliseInfoService;

    public void initializeUserLocaliseInfos () {
        List<UserLocaliseInfoPostDto> infoListForCreation = createUserLocaliseInfoForInitialisation();
        infoListForCreation.forEach(this::accept);
    }

    private List<UserLocaliseInfoPostDto> createUserLocaliseInfoForInitialisation() {
        UserLocaliseInfoPostDto info1 = UserLocaliseInfoPostDto.builder()
                .id(1L)
                .userCountry(UserCountry.ALBANIA)
                .userCurrency(UserCurrency.USD)
                .userLanguage(UserLanguage.ENGLISH)
                .userId(1L)
                .build();

        UserLocaliseInfoPostDto info2 = UserLocaliseInfoPostDto.builder()
                .id(2L)
                .userCountry(UserCountry.RUSSIA)
                .userCurrency(UserCurrency.RUB)
                .userLanguage(UserLanguage.RUSSIAN)
                .userId(2L)
                .build();

        UserLocaliseInfoPostDto info3 = UserLocaliseInfoPostDto.builder()
                .id(3L)
                .userCountry(UserCountry.RUSSIA)
                .userCurrency(UserCurrency.RUB)
                .userLanguage(UserLanguage.RUSSIAN)
                .userId(3L)
                .build();

        UserLocaliseInfoPostDto info4 = UserLocaliseInfoPostDto.builder()
                .id(4L)
                .userCountry(UserCountry.FRANCE)
                .userCurrency(UserCurrency.EUR)
                .userLanguage(UserLanguage.FRENCH)
                .userId(4L)
                .build();

        return Arrays.asList(info1, info2, info3, info4);
    }

    private void accept (UserLocaliseInfoPostDto userLocaliseInfoPostDto) {
        userLocaliseInfoService.placeUserLocaliseInfo(userLocaliseInfoPostDto);
    }
}
