package jm.stockx.api.dao;

import jm.stockx.entity.TokenRecovery;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRecoveryDaoImpl extends AbstractDAO<TokenRecovery> implements TokenRecoveryDAO {

    @Override
    public TokenRecovery getByHashEmail(String hashEmail) {
        return entityManager.createQuery("FROM TokenRecovery where hashEmail = :hashEmail", TokenRecovery.class)
                .setParameter("hashEmail", hashEmail)
                .getSingleResult();
    }

}
