package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BuyingServiceImplTest {

    @InjectMocks
    private BuyingServiceImpl buyingService;

    @Mock
    private BuyingInfoDAO buyingInfoDAO;

    @Test
    public void updateBuyingStatus() {

    }
}