package jm.stockx;

import jm.stockx.api.dao.HistoryViewsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
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
    }
}
