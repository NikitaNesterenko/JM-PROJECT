package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.dto.ItemCategoryDto;
import jm.stockx.dto.ItemInfoGetDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ItemInfoServiceImpl implements ItemInfoService {

    private final ItemInfoDAO itemInfoDAO;

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
    public ItemInfo create(ItemInfo itemInfo) {

        return itemInfoDAO.add(itemInfo);
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
        return itemInfoDAO.getByItemId(itemId);
    }

    @Override
    public List<ItemCategoryDto> getItemCategoryDtoByCategory(ItemCategory category) {
        return itemInfoDAO.getItemCategoryDtoByCategory(category);
    }

    @Override
    public List<ItemInfoGetDto> getListAndOrderByCash(Integer cash) {
        return itemInfoDAO.getListAndOrderByCash(cash);
    }
}
