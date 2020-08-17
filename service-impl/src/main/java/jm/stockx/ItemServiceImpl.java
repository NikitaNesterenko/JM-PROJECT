package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.api.dao.ItemDAO;
import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.BuyingDto;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.dto.SubscriptionDto;
import jm.stockx.entity.*;
import jm.stockx.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDao;
    private final UserDAO userDAO;
    private final MailService mailService;
    private final BuyingInfoDAO buyingInfoDAO;
    private final SellingInfoDAO sellingInfoDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDao,
                           UserDAO userDAO,
                           MailService mailService,
                           BuyingInfoDAO buyingInfoDAO,
                           SellingInfoDAO sellingInfoDAO) {
        this.itemDao = itemDao;
        this.userDAO = userDAO;
        this.mailService = mailService;
        this.sellingInfoDAO = sellingInfoDAO;
        this.buyingInfoDAO = buyingInfoDAO;
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
        Set<Item> boughtItems = new HashSet<>();
        boughtItems.add(item);
        Set<PaymentInfo> paymentInfo = new HashSet<>();
        //TODO : add actual paymentInfo

        BuyingInfo buyingInfo = new BuyingInfo(item);
        buyingInfo.setBoughtItems(boughtItems);
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
    public void subscribeToItem(SubscriptionDto subscriptionDto) {
        User subscriber = userDAO.getById(subscriptionDto.getSubscriberId());
        Item item = itemDao.getById(subscriptionDto.getItemId());
        Set<Item> subscribedItems = new HashSet<>();
        subscribedItems.add(item);

        mailService.sendSimpleMessage(subscriber.getEmail(), "You've subscribe to item!", item.toString());
    }

    @Override
    public void unsubscribeToItem(SubscriptionDto subscriptionDto) {
        User subscriber = userDAO.getById(subscriptionDto.getSubscriberId());
        Item item = itemDao.getById(subscriptionDto.getItemId());
        Set<Item> subscribedItems = new HashSet<>();
        subscribedItems.remove(item);

        mailService.sendSimpleMessage(subscriber.getEmail(), "You've unsubscribe from item!", item.toString());
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
}
