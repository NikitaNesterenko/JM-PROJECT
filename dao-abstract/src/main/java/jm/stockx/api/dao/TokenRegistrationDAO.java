package jm.stockx.api.dao;

import jm.stockx.entity.TokenRegistration;

public interface TokenRegistrationDAO extends GenericDao<TokenRegistration, Long> {
    TokenRegistration getByHashEmail(String hashEmail);
}
