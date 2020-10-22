package jm.stockx.api.dao;

import jm.stockx.dto.security.token.TokenRecoveryDto;
import jm.stockx.entity.TokenRecovery;

public interface TokenRecoveryDAO extends GenericDao<TokenRecovery, Long> {
    TokenRecovery getTokenRecoveryByHashEmail(String hash);

    TokenRecoveryDto getTokenRecoveryDtoByTokenRecoveryId(Long id);
}
