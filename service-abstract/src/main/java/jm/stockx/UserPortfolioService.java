package jm.stockx;

import jm.stockx.entity.UserPortfolio;

public interface UserPortfolioService {
    UserPortfolio getUserPortfolioById(Long id);
    void delete(Long id);
    void create(UserPortfolio userPortfolio);
    UserPortfolio getUserPortfolioByUserId(Long id);
    UserPortfolio update(UserPortfolio userPortfolio);

}
