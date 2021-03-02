package jm.stockx;

import jm.stockx.api.dao.*;
import jm.stockx.dto.item.ItemDto;
import jm.stockx.dto.iteminfo.ItemInfoDto;
import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.userportfolio.BuyingDto;
import jm.stockx.entity.*;
import jm.stockx.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    @Autowired
    public ItemServiceImpl(ItemDAO itemDao,
                           UserDAO userDAO,
                           MailService mailService,
                           BuyingInfoDAO buyingInfoDAO,
                           SellingInfoDAO sellingInfoDAO,
                           ItemInfoDAO itemInfoDAO) {
        this.itemDao = itemDao;
        this.userDAO = userDAO;
        this.mailService = mailService;
        this.sellingInfoDAO = sellingInfoDAO;
        this.buyingInfoDAO = buyingInfoDAO;
        this.itemInfoDAO = itemInfoDAO;
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
        ItemInfoDto itemInfoDto = itemInfoDAO.getItemInfoDtoByItemId(buyingDto.getItemId());
        buyingInfoDAO.addBuyingInfo(itemInfoDto);

        sellingInfoDAO.addSellingInfo(itemInfoDto, buyingDto.getSellerId());

        UserDto buyer = userDAO.getUserDtoByUserId(buyingDto.getBuyerId());
        mailService.sendSimpleMessage(
                buyer.getEmail(), "You've bought item!", itemInfoDto.toString());
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
        return itemDao.getItemDtoByItemId(id);
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
