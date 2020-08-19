package jm.stockx;

import jm.stockx.api.dao.UserPortfolioDaoImpl;
import jm.stockx.entity.UserPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPortfolioServiceImpl implements UserPortfolioService{

    private UserPortfolioDaoImpl userPortfolioDao;

    @Autowired
    public UserPortfolioServiceImpl(UserPortfolioDaoImpl userPortfolioDao) {
        this.userPortfolioDao = userPortfolioDao;
    }

    @Override
    public UserPortfolio getUserPortfolioById(Long id) {
        return userPortfolioDao.getById(id);
    }

    @Override
    public void delete(Long id) {
         userPortfolioDao.deleteById(id);
    }

    @Override
    @Transactional
    public void create(UserPortfolio userPortfolio) {
        userPortfolioDao.add(userPortfolio);
    }

   @Override
    public UserPortfolio getUserPortfolioByUserId(Long id) {
        return null; //userPortfolioDao.getUserPortfolioByUserId(id);
    }
}
