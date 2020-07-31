package jm.stockx.api.dao;

import jm.stockx.entity.HistoryViews;

public interface HistoryViewsDAO extends GenericDao<HistoryViews, Long> {
    void clearHistoryViews();
}
