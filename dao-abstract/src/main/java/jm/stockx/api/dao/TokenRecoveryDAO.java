package jm.stockx.api.dao;

import jm.stockx.dto.TokenRecoveryDto;
import jm.stockx.entity.TokenRecovery;

public interface TokenRecoveryDAO extends GenericDao<TokenRecovery, Long> {
    TokenRecovery getByHashEmail(String hash);
    TokenRecoveryDto getTokenRecoveryDtoById(Long id);
}
