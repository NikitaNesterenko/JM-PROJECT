import jm.stockx.ItemInfoServiceImpl;
import jm.stockx.ItemServiceImpl;
import jm.stockx.dto.itemInfo.ItemInfoDto;
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
    private ItemInfoServiceImpl itemService;

    @Mock
    private jm.stockx.api.dao.ItemInfoDAO itemInfoDao;

    @Test
    public void getTopItemsByStyle() {
        int top = 5;
        List<ItemInfoDto> items = new ArrayList<>();
        items.add(new ItemInfoDto());
        items.add(new ItemInfoDto());
        items.add(new ItemInfoDto());
        items.add(new ItemInfoDto());
        items.add(new ItemInfoDto());
        when(itemInfoDao.getMostPopularItemByStyleId(1L, top)).thenReturn(items);
        assertEquals(itemService.getMostPopularItemByStyleId(1L, top).size(), top);
        verify(itemInfoDao).getMostPopularItemByStyleId(1L,top);
    }
}