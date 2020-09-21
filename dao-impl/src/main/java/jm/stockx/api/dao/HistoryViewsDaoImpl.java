package jm.stockx.api.dao;

import jm.stockx.entity.HistoryViews;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class HistoryViewsDaoImpl extends AbstractDAO<HistoryViews, Long> implements HistoryViewsDAO {
    @Override
    @Transactional
    public void clearHistoryOfViews() {
        entityManager.createQuery("" +
                "DELETE FROM HistoryViews " +
                "WHERE item IN " +
                "(SELECT item " +
                "FROM HistoryViews " +
                "GROUP BY item " +
                "HAVING COUNT(item) > 4)")
                .executeUpdate();
    }
}
