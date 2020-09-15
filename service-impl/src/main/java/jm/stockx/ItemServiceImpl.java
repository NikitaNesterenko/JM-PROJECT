package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.api.dao.ItemDAO;
import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.BuyingDto;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import jm.stockx.entity.PaymentInfo;
import jm.stockx.entity.SellingInfo;
import jm.stockx.entity.ShoeSize;
import jm.stockx.entity.User;
import jm.stockx.enums.Status;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDao;
    private final UserDAO userDAO;
    private final MailService mailService;
    private final BuyingInfoDAO buyingInfoDAO;
    private final SellingInfoDAO sellingInfoDAO;
    private final ShoeSizeServiceImpl shoeSizeService;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDao,
                           UserDAO userDAO,
                           MailService mailService,
                           BuyingInfoDAO buyingInfoDAO,
                           SellingInfoDAO sellingInfoDAO,
                           ShoeSizeServiceImpl shoeSizeService) {
        this.itemDao = itemDao;
        this.userDAO = userDAO;
        this.mailService = mailService;
        this.sellingInfoDAO = sellingInfoDAO;
        this.buyingInfoDAO = buyingInfoDAO;
        this.shoeSizeService = shoeSizeService;
    }

    @Override
    public Item getItemByName(String name) {
        return itemDao.getByName(name).orElse(null);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Item get(Long id) {
        return itemDao.getById(id);
    }

    @Override
    public void create(Item item) {
        itemDao.add(item);
    }

    @Override
    public void update(Item item) {
        itemDao.update(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.deleteById(id);
    }

    @Override
    public PageDto<ItemDto> getPageOfItems(Integer page, String search, Integer size) {
        List<ItemDto> foundItems = itemDao.searchItem(search, page, size);
        int sizeItems = foundItems.size();
        int totalEntitiesCount = foundItems.size();
        int pageCount = totalEntitiesCount / size;
        return new PageDto<>(sizeItems, page, pageCount,
                size, foundItems);
    }

    @Override
    public void buyItem(BuyingDto buyingDto) {
        // TODO: payment
        User buyer = userDAO.getById(buyingDto.getBuyerId());
        Item item = itemDao.getById(buyingDto.getItemId());
        Set<Item> bougthItems = new HashSet<>();
        bougthItems.add(item);
        Set<PaymentInfo> paymentInfo = new HashSet<>();
        //TODO : add actual paymentInfo

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

        mailService.sendSimpleMessage(buyer.getEmail(), "You've bought item!", item.toString());
    }

    @Override
    public List<Item> getTopItemsByStyle(Long styleId, Integer topLimit) {
        return itemDao.getTopItemsByStyleFromSellingInfo(styleId, topLimit);
    }

    @Override
    public List<Item> getNotReleasedItems() {
        return itemDao.getNotReleasedItems();
    }

    @Override
    public List<Item> getNotReleasedItemsByBrand(Brand brand) {
        return itemDao.getNotReleasedItemsByBrand(brand);
    }

    @Override
    public boolean isItemExist(Long id) {
        return itemDao.doesItExistEntity(id);
    }

    @Override
    public Map<String, Money> getTheItemDTOForSizeNameBrand(String brand, String name) {
        List<ShoeSize> shoeSizes = shoeSizeService.getAll();
        HashMap<String, Money> lowestAsksBySize = new HashMap<>();
        for (ShoeSize s : shoeSizes) {
            String shoeSize = s.getSize().toString();
            Money lowestAsk = itemDao.getItemBySizeBrandName(brand, name, shoeSize).getLowestAsk();
            lowestAsksBySize.put(shoeSize, lowestAsk);
        }
        return lowestAsksBySize;
    }

}
