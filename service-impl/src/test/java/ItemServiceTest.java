import jm.stockx.ItemServiceImpl;
import jm.stockx.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private jm.stockx.api.dao.ItemDAO itemDao;

    @Test
    public void getTopItemsByStyle() {
        int top = 5;
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());
        items.add(new Item());
        items.add(new Item());
        items.add(new Item());
        when(itemDao.getTopItemsByStyleFromSellingInfo(1L, top)).thenReturn(items);
        assertEquals(itemService.getTopItemsByStyle(1L, top).size(), top);
        verify(itemDao).getTopItemsByStyleFromSellingInfo(1L,top);
    }
}