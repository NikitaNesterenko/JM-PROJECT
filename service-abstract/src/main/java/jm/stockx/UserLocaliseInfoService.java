package jm.stockx;

import jm.stockx.entity.UserLocaliseInfo;

import java.util.List;

public interface UserLocaliseInfoService {

    List<UserLocaliseInfo> getAll();

    void create(UserLocaliseInfo userLocaliseInfo);

    void update(UserLocaliseInfo userLocaliseInfo);

    String getMoneyTagByUserId(Long id);

}
