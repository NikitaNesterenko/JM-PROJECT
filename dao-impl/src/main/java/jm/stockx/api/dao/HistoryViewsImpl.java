package jm.stockx.api.dao;

import jm.stockx.entity.HistoryViews;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HistoryViewsImpl extends AbstractDAO<HistoryViews, Long> implements HistoryViewsDAO{

    @Override
    @Transactional
    public void clearHistoryViews() {
        String query = "DELETE FROM history_views " +
                "WHERE item_id in " +
                "(SELECT item_id FROM " +
                "(SELECT * FROM history_views) as h " +
                "group by item_id " +
                "HAVING COUNT(item_id) > 4)";
        entityManager.createNativeQuery(query).executeUpdate();
    }


}
