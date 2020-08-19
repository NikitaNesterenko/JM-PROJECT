package jm.stockx.api.dao;

import jm.stockx.entity.UserPortfolio;
import org.springframework.stereotype.Repository;

/**
 * Не смог написать запрос по поиску строки по id пользователя. спросить, пригодится ли вообще этот функционал.
*/

@Repository
public class UserPortfolioDaoImpl extends AbstractDAO<UserPortfolio, Long> {

    /*public UserPortfolio getUserPortfolioByUserId(Long id) {
        String query = "FROM UserPortfolio user_port where user_port.user_id = :id";
        return  (UserPortfolio) entityManager.createQuery(query)
                .setParameter("id", id)
                .getSingleResult();

    }*/

}
