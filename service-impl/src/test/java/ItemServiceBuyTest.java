import jm.stockx.ItemServiceImpl;
import jm.stockx.dto.BuyingDto;
import jm.stockx.entity.*;
import jm.stockx.enums.ItemColors;
import jm.stockx.enums.Status;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@RunWith(SpringRunner.class)
public class ItemServiceBuyTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private jm.stockx.api.dao.UserDAO userDAO;
    @Mock
    private jm.stockx.api.dao.BuyingInfoDAO buyingInfoDAO;
    @Mock
    private jm.stockx.api.dao.SellingInfoDAO sellingInfoDAO;

    private final String name = "Item_Name";
    private final Money money = Money.parse("USD 23.87");
    private final Money retailPrice = Money.parse("USD 40.87");
    private final Money lowestAsk = Money.parse("USD 23.00");
    private final Money highestBid = Money.parse("USD 25.00");
    private final LocalDate releaseDate = LocalDate.of(2020, 8, 23);
    private final String condition = "GOOD";
    private final String description = "Some_Discription";
    private final Brand brand = new Brand("Some_brand");
    private final String itemImageUrl = "/some_brad";
    private final Style style = new Style("some_style");
    private final ItemColors itemColors = ItemColors.BLACK;

    @Test
    public void buyItem() {

        Item item = new Item(name, money, retailPrice, lowestAsk, highestBid,
                             releaseDate, condition, description, brand, itemImageUrl, style);
        BuyingDto buyingDto = new BuyingDto(1L, 1L, 1L, 1L);
        Set<Item> bougthItems = new HashSet<>();
        bougthItems.add(item);
        Set<PaymentInfo> paymentInfo = new HashSet<>();
        BuyingInfo buyingInfo = new BuyingInfo(item);
        buyingInfo.setBoughtItems(bougthItems);
        buyingInfo.setPaymentsInfo(paymentInfo);
        buyingInfo.setStatus(Status.ACCEPTED);
        buyingInfoDAO.add(buyingInfo);
        User seller = userDAO.getById(buyingDto.getBuyerId());
        SellingInfo sellingInfo = new SellingInfo(seller, item);
        sellingInfo.setUser(seller);
        sellingInfo.setStatus(Status.ACCEPTED);
        sellingInfoDAO.add(sellingInfo);
    }
}
