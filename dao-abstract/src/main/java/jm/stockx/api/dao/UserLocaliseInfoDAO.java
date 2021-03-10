package jm.stockx.api.dao;

import jm.stockx.entity.UserLocaliseInfo;

public interface UserLocaliseInfoDAO extends GenericDao<UserLocaliseInfo, Long>{

    String getMoneyTagByUserId (Long id);
}
