package jm.stockx;

import jm.stockx.api.dao.BuyingDAO;
import jm.stockx.api.dao.ItemDAO;
import jm.stockx.api.dao.SellingDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.BuyingDto;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.entity.*;
import jm.stockx.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDao;
    private final UserDAO userDAO;
    private final MailService mailService;
    private final BuyingDAO buyingDAO;
    private final SellingDAO sellingDAO;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDao, UserDAO userDAO, MailService mailService, BuyingDAO buyingDAO, SellingDAO sellingDAO) {
        this.itemDao = itemDao;
        this.userDAO = userDAO;
        this.mailService = mailService;
        this.buyingDAO = buyingDAO;
        this.sellingDAO = sellingDAO;
    }

    @Override
    public Item getItemByName(String name) {
        return itemDao.getItemByName(name).orElse(null);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Item get(Long id) {
        return itemDao.getById(id);
    }

    @Transactional
    @Override
    public void create(Item item) {
        itemDao.add(item);
    }

    @Transactional
    @Override
    public void update(Item item) {
        itemDao.merge(item);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        itemDao.deleteById(id);
    }

    @Transactional
    @Override
    public void addItemImage(Long id, byte[] array) {
        itemDao.addItemImage(id, array);
    }

    @Transactional
    @Override
    public byte[] getItemImage(Long id) {
        return itemDao.getItemImage(id);
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
        buyingDAO.add(buyingInfo);

        User seller = userDAO.getById(buyingDto.getBuyerId());
        SellingInfo sellingInfo = new SellingInfo(item);
        sellingInfo.setUser(seller);
        sellingInfo.setStatus(Status.ACCEPTED);
        sellingDAO.add(sellingInfo);

        mailService.sendSimpleMessage(buyer.getEmail(), "You've bought item!", item.toString());
    }
}
