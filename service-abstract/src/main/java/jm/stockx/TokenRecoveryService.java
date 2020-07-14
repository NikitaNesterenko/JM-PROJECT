package jm.stockx;

import jm.stockx.entity.TokenRecovery;

public interface TokenRecoveryService {

    void createToken(TokenRecovery tokenRecovery);

    void deleteToken(Long id);

    TokenRecovery getTokenById(Long id);

    TokenRecovery getTokenByHashEmail(String hashEmail);

}
