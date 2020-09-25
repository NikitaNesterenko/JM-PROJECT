package jm.stockx;

import jm.stockx.entity.TokenRegistration;

public interface TokenRegistrationService {
    void createToken(TokenRegistration tokenRegistration);

    void deleteToken(Long id);

    TokenRegistration getTokenById(Long id);

    TokenRegistration getTokenByHashEmail(String hashEmail);

}
