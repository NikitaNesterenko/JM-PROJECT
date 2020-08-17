package jm.stockx;

import jm.stockx.api.dao.HistoryViewsDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class HistoryViewsServiceImpl implements HistoryViewsService{
    private final HistoryViewsDAO historyViewsDAO;

    @Autowired
    public HistoryViewsServiceImpl(HistoryViewsDAO historyViewsDAO){
        this.historyViewsDAO = historyViewsDAO;
    }

    @Override
    @Scheduled(cron = "* * * * * sun")
    public void clearHistoryViews() {
        historyViewsDAO.clearHistoryOfViews();
        log.info("История просмотров очищена");
    }
}
