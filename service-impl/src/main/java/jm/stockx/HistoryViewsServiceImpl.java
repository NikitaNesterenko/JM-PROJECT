package jm.stockx;

import jm.stockx.api.dao.HistoryViewsDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Slf4j
public class HistoryViewsServiceImpl implements HistoryViewsService {

    private final HistoryViewsDAO historyViewsDAO;

    @Autowired
    public HistoryViewsServiceImpl(HistoryViewsDAO historyViewsDAO){
        this.historyViewsDAO = historyViewsDAO;
    }

    @Override
    @Scheduled(cron = "* * * * * sun")
    public void clearHistoryViews() {
        historyViewsDAO.clearHistoryViews();
        log.info("DELETE FROM history_views " +
                "WHERE item_id in " +
                "(SELECT item_id FROM " +
                "(SELECT * FROM history_views) as h " +
                "group by item_id " +
                "HAVING COUNT(item_id) > 4) : " +
                "История просмотров очищена");
    }
}
