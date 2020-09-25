package jm.stockx.api.dao;

import jm.stockx.entity.TokenRegistration;
import jm.stockx.entity.User;

public interface TokenRegistrationDAO extends GenericDao<TokenRegistration, Long>{
    TokenRegistration getByHashEmail(String hashEmail);
    boolean isActive(User user);
}
