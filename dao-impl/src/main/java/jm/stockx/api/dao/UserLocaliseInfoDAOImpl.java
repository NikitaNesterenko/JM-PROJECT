package jm.stockx.api.dao;

import jm.stockx.entity.UserLocaliseInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserLocaliseInfoDAOImpl extends AbstractDAO<UserLocaliseInfo, Long> implements UserLocaliseInfoDAO{

    @Override
    public String getMoneyTagByUserId(Long id) {
        return entityManager.createQuery("SELECT l.userCurrency " +
                "FROM UserLocaliseInfo AS l WHERE l.user.id =: id")
                .setParameter("id", id)
                .getSingleResult()
                .toString();
    }
}
