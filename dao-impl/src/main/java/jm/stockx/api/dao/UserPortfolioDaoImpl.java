package jm.stockx.api.dao;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.UserPortfolio;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserPortfolioDaoImpl extends AbstractDAO<UserPortfolio, Long> {

    public UserPortfolio getUserPortfolioByUserId(Long id) {
        String query = "select user_port FROM UserPortfolio as user_port where user_port.id = :id";
        return  (UserPortfolio) entityManager.createQuery(query)
                .setParameter("id", id)
                .getSingleResult();

    }

    public List<BuyingInfo> getBuyingInfoByPortfolioId(Long id) {
        String query = "select us.buyingInfo from UserPortfolio as up  inner join up.user as us where up.id = :id";
        return  entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();

    }

    public List<Item> getItemByPortfolioId(Long id) {
        String query = "" +
                "select bi.boughtItems " +
                "from UserPortfolio as up " +
                "left join up.user as us " +
                "join us.buyingInfo as bi " +
                "where up.id = :id";
        return entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();
    }

}
