package jm.stockx.api.dao;

import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.UserPortfolio;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserPortfolioDaoImpl extends AbstractDAO<UserPortfolio, Long> {

    // TODO: Использование Entity
    public UserPortfolio getUserPortfolioById(Long id) {
        return  entityManager.createQuery("" +
                "SELECT user_port " +
                "FROM   UserPortfolio AS user_port " +
                "WHERE  user_port.id = :id", UserPortfolio.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    public List<BuyingInfo> getBuyingInfoByPortfolioId(Long id) {
        return  entityManager.createQuery("" +
                "SELECT     us.buyingInfo " +
                "FROM       UserPortfolio AS up " +
                "INNER JOIN up.user AS us " +
                "WHERE      up.id = :id")
                .setParameter("id", id)
                .getResultList();

    }

    public List<Item> getItemByPortfolioId(Long id) {
        return entityManager.createQuery("" +
                "SELECT    bi.boughtItemsInfo " +
                "FROM      UserPortfolio AS up " +
                "LEFT JOIN up.user AS us " +
                "JOIN      us.buyingInfo AS bi " +
                "WHERE     up.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

}
