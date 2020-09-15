package jm.stockx.api.dao;

import jm.stockx.entity.TokenRegistration;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRegistrationDaoImpl extends AbstractDAO<TokenRegistration, Long> implements TokenRegistrationDAO {
    @Override
    public TokenRegistration getByHashEmail(String hashEmail) {
        return entityManager.createQuery("" +
                "FROM TokenRegistration AS t " +
                "WHERE t.hashEmail = :hashEmail", TokenRegistration.class)
                .setParameter("hashEmail", hashEmail)
                .getSingleResult();
    }

    @Override
    public boolean isActive(User user) {
        return entityManager.createQuery("" +
                "FROM TokenRegistration AS t " +
                "WHERE t.user = :user", TokenRegistration.class)
                .setParameter("user", user)
                .getSingleResult() == null;
    }

}
