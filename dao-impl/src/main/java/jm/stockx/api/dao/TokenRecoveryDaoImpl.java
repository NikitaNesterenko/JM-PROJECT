package jm.stockx.api.dao;

import jm.stockx.dto.TokenRecoveryDto;
import jm.stockx.entity.TokenRecovery;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRecoveryDaoImpl extends AbstractDAO<TokenRecovery, Long> implements TokenRecoveryDAO {

    @Override
    public TokenRecovery getByHashEmail(String hashEmail) {
        return entityManager.createQuery("" +
                "FROM TokenRecovery AS t " +
                "WHERE t.hashEmail = :hashEmail", TokenRecovery.class)
                .setParameter("hashEmail", hashEmail)
                .getSingleResult();
    }

    @Override
    public TokenRecoveryDto getTokenRecoveryDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT new jm.stockx.dto.TokenRecoveryDto(" +
                "u.id," +
                "u.user," +
                "u.hash," +
                "u.hashEmail," +
                "u.startTime)" +
                "FROM TokenRecovery AS u " +
                "WHERE u.id =: id", TokenRecoveryDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
