package jm.stockx;

import jm.stockx.dto.TokenRecoveryDto;
import jm.stockx.entity.TokenRecovery;

public interface TokenRecoveryService {

    void createToken(TokenRecovery tokenRecovery);

    void deleteToken(Long id);

    TokenRecoveryDto getTokenById(Long id);

    TokenRecovery getTokenByHashEmail(String hashEmail);

}
