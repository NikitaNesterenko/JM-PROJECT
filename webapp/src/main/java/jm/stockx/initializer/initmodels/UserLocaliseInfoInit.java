package jm.stockx.initializer.initmodels;

import jm.stockx.UserLocaliseInfoService;
import jm.stockx.UserService;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.entity.UserLocaliseInfo;
import jm.stockx.enums.UserCountry;
import jm.stockx.enums.UserCurrency;
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
    private final UserDAO userDAO;

    public void initializeUserLocaliseInfos () {
        List<UserLocaliseInfo> infoListForCreation = createUserLocaliseInfoForInitialisation();
        infoListForCreation.forEach(userLocaliseInfoService::create);
    }

    private List<UserLocaliseInfo> createUserLocaliseInfoForInitialisation() {
        System.out.println(userDAO.getUserById(1L));
        UserLocaliseInfo info1 = UserLocaliseInfo.builder()
                .userCountry(UserCountry.ALBANIA)
                .userCurrency(UserCurrency.USD)
                .user(userDAO.getUserById(1L))
                .build();

        UserLocaliseInfo info2 = UserLocaliseInfo.builder()
                .userCountry(UserCountry.RUSSIA)
                .userCurrency(UserCurrency.RUB)
                .user(userDAO.getUserById(2L))
                .build();

        UserLocaliseInfo info3 = UserLocaliseInfo.builder()
                .userCountry(UserCountry.RUSSIA)
                .userCurrency(UserCurrency.RUB)
                .user(userDAO.getUserById(3L))
                .build();

        UserLocaliseInfo info4 = UserLocaliseInfo.builder()
                .userCountry(UserCountry.FRANCE)
                .userCurrency(UserCurrency.EUR)
                .user(userDAO.getUserById(4L))
                .build();

        return Arrays.asList(info1, info2, info3, info4);
    }

}
