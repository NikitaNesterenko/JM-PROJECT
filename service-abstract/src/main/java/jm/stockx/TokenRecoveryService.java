package jm.stockx;

import jm.stockx.dto.security.token.TokenRecoveryDto;
import jm.stockx.entity.TokenRecovery;

public interface TokenRecoveryService {

    void createToken(TokenRecovery tokenRecovery);

    void deleteToken(Long id);

    TokenRecoveryDto getTokenRecoveryDtoByTokenRecoveryId(Long id);

    TokenRecovery getTokenRecoveryByHashEmail(String hashEmail);

}
