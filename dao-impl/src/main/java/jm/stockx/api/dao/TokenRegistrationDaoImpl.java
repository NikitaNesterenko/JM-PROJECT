package jm.stockx.api.dao;

import jm.stockx.entity.TokenRegistration;
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
}
