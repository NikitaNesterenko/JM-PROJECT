package jm.stockx.api.dao;

import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.entity.UserLocaliseInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserLocaliseInfoDAOImpl extends AbstractDAO<UserLocaliseInfo, Long> implements UserLocaliseInfoDAO{
    @Override
    public void deleteByUserName(String userName) {
        entityManager.createQuery("" +
                "DELETE FROM UserLocaliseInfo " +
                "WHERE user.username =: username")
                .setParameter("username", userName)
                .executeUpdate();
    }

    @Override
    public UserLocaliseInfoDto getUserLocaliseInfoDtoByUserName(String userName) {
        return entityManager.createQuery("SELECT NEW jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto(" +
                "l.id," +
                "l.userCountry," +
                "l.userCurrency," +
                "l.userLanguage," +
                "l.user.username)" +
                "FROM UserLocaliseInfo AS l, User AS u " +
                "WHERE u.username =: username", UserLocaliseInfoDto.class)
                .setParameter("username", userName)
                .getSingleResult();
    }
}
