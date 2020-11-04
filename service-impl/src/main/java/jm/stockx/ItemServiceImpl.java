package jm.stockx;

import jm.stockx.api.dao.*;
import jm.stockx.dto.item.ItemDto;
import jm.stockx.dto.itemInfo.ItemInfoDto;
import jm.stockx.dto.userPortfolio.BuyingDto;
import jm.stockx.entity.*;
import jm.stockx.enums.ItemCategory;
import jm.stockx.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDao;
    private final UserDAO userDAO;
    private final ItemInfoDAO itemInfoDAO;
    private final MailService mailService;
    private final BuyingInfoDAO buyingInfoDAO;
    private final SellingInfoDAO sellingInfoDAO;
    private final EmailService emailService;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDao,
                           UserDAO userDAO,
                           MailService mailService,
                           BuyingInfoDAO buyingInfoDAO,
                           SellingInfoDAO sellingInfoDAO,
                           ItemInfoDAO itemInfoDAO,
                           EmailService emailService) {
        this.itemDao = itemDao;
        this.userDAO = userDAO;
        this.mailService = mailService;
        this.sellingInfoDAO = sellingInfoDAO;
        this.buyingInfoDAO = buyingInfoDAO;
        this.itemInfoDAO = itemInfoDAO;
        this.emailService = emailService;
    }

    @Override
    public Set<Item> getAll() {
        return new HashSet<>(itemDao.getAll());
    }

    @Override
    public ItemDto getItemDtoById(Long id) {
        return itemDao.getItemDtoByItemId(id);
    }

    @Override
    public void create(Item item) {

        itemDao.add(item);

        ItemInfo itemInfo = itemInfoDAO.getById(item.getId());

        ItemCategory itemCategory = itemInfo.getItemCategory();

        BuyingInfo buyingInfo = itemInfo.getBuyingInfo();

        List<User> users = userDAO.getUsersByBuyingInfo(buyingInfo);

        for (User user : users) {
            emailService.sendSimpleEmail(user.getEmail(), "New item", item.getName());
        }
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
    public void buyItem(BuyingDto buyingDto) {
        // TODO: payment
        User buyer = userDAO.getById(buyingDto.getBuyerId());
        ItemInfo itemInfo = itemInfoDAO.getById(buyingDto.getItemId());
        ItemInfoDto itemInfoDto = new ItemInfoDto(itemInfoDAO.getItemInfoByItemId(buyingDto.getItemId()));
        Set<ItemInfo> bougthItems = new HashSet<>();
        bougthItems.add(itemInfo);
        Set<PaymentInfo> paymentInfo = new HashSet<>();
        //TODO : add actual paymentInfo

        BuyingInfo buyingInfo = new BuyingInfo(itemInfoDto);
        buyingInfo.setBoughtItemsInfo(bougthItems);
        buyingInfo.setPaymentsInfo(paymentInfo);
        buyingInfo.setStatus(Status.ACCEPTED);
        buyingInfoDAO.add(buyingInfo);

        User seller = userDAO.getById(buyingDto.getBuyerId());
        SellingInfo sellingInfo = new SellingInfo(seller, itemInfoDto, itemInfo);
        sellingInfo.setUser(seller);
        sellingInfo.setStatus(Status.ACCEPTED);
        sellingInfoDAO.add(sellingInfo);

        mailService.sendSimpleMessage(buyer.getEmail(), "You've bought item!", itemInfo.toString());
    }


    @Override
    public boolean isItemExist(Long id) {
        return itemDao.doesItExistEntity(id);
    }

    @Override
    public ItemDto getItemDtoByItemName(String name) {
        return itemDao.getItemDtoByItemName(name);
    }

    @Override
    public ItemDto getItemDtoByItemId(Long id) {
        return getItemDtoByItemId(id);
    }

    @Override
    public Item getItemByName(String name) {
        return itemDao.getItemByName(name);
    }

    @Override
    public Item getItemById(Long id) {
        return itemDao.getItemById(id);
    }

}
