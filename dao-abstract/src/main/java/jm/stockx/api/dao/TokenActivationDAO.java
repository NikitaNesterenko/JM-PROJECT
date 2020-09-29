package jm.stockx.api.dao;

import jm.stockx.entity.TokenRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenActivationDAO extends JpaRepository<TokenRegistration, Long> {
    TokenRegistration getByHashEmail(String hashEmail);
}
