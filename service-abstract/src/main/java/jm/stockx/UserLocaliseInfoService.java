package jm.stockx;

import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.entity.UserLocaliseInfo;

public interface UserLocaliseInfoService {

    void create(UserLocaliseInfo userLocaliseInfo);

    void update(UserLocaliseInfo userLocaliseInfo);

    void deleteByUserName(String userName);

    UserLocaliseInfoDto getUserLocaliseInfoDtoByUserName (String userName);
}
