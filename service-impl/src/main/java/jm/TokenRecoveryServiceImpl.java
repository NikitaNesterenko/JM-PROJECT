package jm;

import jm.api.dao.TokenRecoveryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TokenRecoveryServiceImpl implements TokenRecoveryService {
    private final TokenRecoveryDAO tokenDao;

    @Autowired
    public TokenRecoveryServiceImpl(TokenRecoveryDAO tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Transactional
    @Override
    public void createToken(TokenRecovery tokenRecovery) {
        tokenDao.add(tokenRecovery);
    }

    @Transactional
    @Override
    public void deleteToken(Long id) {
        tokenDao.deleteById(id);
    }

    @Override
    public TokenRecovery getTokenById(Long id) {
        return tokenDao.getById(id);
    }

    @Override
    public TokenRecovery getTokenByHashEmail(String hash) {
        return tokenDao.getByHashEmail(hash);
    }
}
