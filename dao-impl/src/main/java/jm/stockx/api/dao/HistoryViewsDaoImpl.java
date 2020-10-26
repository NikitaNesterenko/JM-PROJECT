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
                "WHERE itemInfo IN " +
                "(SELECT itemInfo " +
                "FROM HistoryViews " +
                "GROUP BY itemInfo " +
                "HAVING COUNT(itemInfo) > 4)")
                .executeUpdate();
    }
}
