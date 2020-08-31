package jm.stockx;

import jm.stockx.api.dao.BidDAO;
import jm.stockx.api.dao.ItemDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.entity.Bid;
import jm.stockx.entity.Item;
import jm.stockx.entity.User;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BidServiceTest {

    User user = new User("Ivan", "Ivanov", "123");
    Item item = new Item("AIR MAX");
    Bid bid = new Bid(Money.parse("USD 300"), true, user, item);

    @InjectMocks
    private BidServiceImpl bidService;

    @Mock
    private BidDAO bidDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ItemDAO itemDAO;

    @Test
    public void getBidById() {
        when(bidDAO.getById(1L)).thenReturn(bid);
        assertEquals(bidService.get(1L), bid);
        verify(bidDAO).getById(1L);
    }


    @Test
    public void create_addBid() {
        bidService.create(bid);
        verify(bidDAO).add(bid);
    }


    @Test
    public void update() {
    }

    @Test
    public void delete() {
        bidService.delete(1L);
        verify(bidDAO).deleteById(1L);
    }

    @Test
    public void isBidExist() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void get() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void create() {
    }

    @Test
    public void testCreate() {
    }
}