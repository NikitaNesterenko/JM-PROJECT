package jm.stockx;

import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoPostDto;
import jm.stockx.entity.UserLocaliseInfo;

import java.util.List;

public interface UserLocaliseInfoService {

    List<UserLocaliseInfo> getAll();

    void create(UserLocaliseInfo userLocaliseInfo);

    void update(UserLocaliseInfo userLocaliseInfo);

    void deleteByUserName(String userName);

    UserLocaliseInfoDto getUserLocaliseInfoDtoByUserName (String userName);

    void placeUserLocaliseInfo(UserLocaliseInfoPostDto userLocaliseInfoPostDto);
}
