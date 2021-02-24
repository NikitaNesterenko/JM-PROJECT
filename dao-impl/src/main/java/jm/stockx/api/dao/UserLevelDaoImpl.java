package jm.stockx.api.dao;

import jm.stockx.entity.UserLevel;
import org.springframework.stereotype.Repository;

@Repository
public class UserLevelDaoImpl extends AbstractDAO<UserLevel, Long> implements UserLevelDAO {

    @Override
    public UserLevel getUserLevelByUserId(Long id) {
        return entityManager.createQuery("" +
                "FROM UserLevel AS ul WHERE ul.id =: id", UserLevel.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
