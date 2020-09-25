package jm.stockx;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.UserPortfolio;

import java.util.List;

public interface UserPortfolioService {

    UserPortfolio getUserPortfolioById(Long id);

    void delete(Long id);

    void create(UserPortfolio userPortfolio);

    UserPortfolio getUserPortfolioByUserId(Long id);

    UserPortfolio update(UserPortfolio userPortfolio);

    List<BuyingInfo> getBuyingInfoByPortfolioId(Long id);

    List<Item> getItemByPortfolioId(Long id);

    void addItemToPortfolio(Long portfolioId, Long itemId);

}
