package jm.stockx;

import jm.api.dao.ItemDAO;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDao;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public Item getItemByName(String name) {
        return itemDao.getItemByName(name).get();
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
    public List<Item> getTopItemsByStyle(Long styleId, Integer topLimit) {
        return itemDao.getTopItemsByStyleFromSellingInfo(styleId, topLimit);
    }
}
