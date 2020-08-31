package jm.stockx;

import jm.stockx.api.dao.HistoryViewsDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class HistoryViewsServiceImplTest {

    @InjectMocks
    private HistoryViewsServiceImpl historyViewsService;

    @Mock
    private HistoryViewsDAO historyViewsDAO;

    @Test
    public void clearHistoryViews() {
        historyViewsService.clearHistoryViews();
        verify(historyViewsDAO).clearHistoryOfViews();
    }
}