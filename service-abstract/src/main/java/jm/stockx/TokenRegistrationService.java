package jm.stockx;

import jm.stockx.entity.TokenRegistration;
import jm.stockx.entity.User;

public interface TokenRegistrationService {
    void createToken(TokenRegistration tokenRegistration);

    void deleteToken(Long id);

    TokenRegistration getTokenById(Long id);

    TokenRegistration getTokenByHashEmail(String hashEmail);

    boolean isActive(User user);
}
