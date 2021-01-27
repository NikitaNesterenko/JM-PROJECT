package jm.stockx.api.dao;

import jm.stockx.dto.security.token.TokenRecoveryDto;
import jm.stockx.entity.TokenRecovery;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRecoveryDaoImpl extends AbstractDAO<TokenRecovery, Long> implements TokenRecoveryDAO {

    @Override
    public TokenRecovery getTokenRecoveryByHashEmail(String hashEmail) {

        // TODO: Использование Entity
        return entityManager.createQuery("" +
                "FROM TokenRecovery AS t " +
                "WHERE t.hashEmail = :hashEmail", TokenRecovery.class)
                .setParameter("hashEmail", hashEmail)
                .getSingleResult();
    }

    @Override
    public TokenRecoveryDto getTokenRecoveryDtoByTokenRecoveryId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.security.token.TokenRecoveryDto(" +
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
