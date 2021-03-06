package jm.stockx.api.dao;

import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.entity.UserLocaliseInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocaliseInfoDAO extends GenericDao<UserLocaliseInfo, Long>{
    void deleteByUserName(String userName);

    UserLocaliseInfoDto getUserLocaliseInfoDtoByUserName (String userName);
}
