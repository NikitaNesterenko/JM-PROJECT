package jm.stockx.api.dao;

import jm.stockx.entity.TokenRecovery;

public interface TokenRecoveryDAO extends GenericDao<TokenRecovery, Long> {
    TokenRecovery getByHashEmail(String hash);
}
