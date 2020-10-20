package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemInfoServiceImpl implements ItemInfoService {

    private ItemInfoDAO itemInfoDAO;

    @Autowired
    public ItemInfoServiceImpl(ItemInfoDAO itemInfoDAO) {
        this.itemInfoDAO = itemInfoDAO;
    }

    @Override
    public List<ItemInfo> getAllNews() {
        return itemInfoDAO.getAll();
    }

    @Override
    public ItemInfo get(Long id) {
        return itemInfoDAO.getById(id);
    }

    @Override
    public void create(ItemInfo itemInfo) {
        itemInfoDAO.add(itemInfo);
    }

    @Override
    public void update(ItemInfo itemInfo) {
        itemInfoDAO.update(itemInfo);
    }

    @Override
    public void delete(Long id) {
        itemInfoDAO.deleteById(id);
    }

    @Override
    public ItemInfo getItemInfoByItemId(Long itemId) {
        return itemInfoDAO.getItemInfoByItemId(itemId);
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category) {
        return itemInfoDAO.getItemInfoCardDtoByItemCategory(category);
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price) {
        return itemInfoDAO.getItemInfoCardDtoMorePrice(price);
    }
}
