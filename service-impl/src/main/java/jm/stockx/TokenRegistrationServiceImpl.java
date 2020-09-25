package jm.stockx;

import jm.stockx.api.dao.TokenRegistrationDAO;
import jm.stockx.entity.TokenRegistration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenRegistrationServiceImpl implements TokenRegistrationService {

    private final TokenRegistrationDAO tokenDao;


    public TokenRegistrationServiceImpl(TokenRegistrationDAO tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Override
    public void createToken(TokenRegistration tokenRegistration) {
        tokenDao.add(tokenRegistration);
    }

    @Override
    public void deleteToken(Long id) {
        tokenDao.deleteById(id);
    }

    @Override
    public TokenRegistration getTokenById(Long id) {
        return tokenDao.getById(id);
    }

    @Override
    public TokenRegistration getTokenByHashEmail(String hashEmail) {
        return tokenDao.getByHashEmail(hashEmail);
    }
}
