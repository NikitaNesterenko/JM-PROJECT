package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class ItemInfoServiceImpl implements ItemInfoService {

    private ItemInfoDAO itemInfoDAO;

    @Autowired
    public ItemInfoServiceImpl(ItemInfoDAO itemInfoDAO) {
        this.itemInfoDAO = itemInfoDAO;
    }

    @Override
    public Set<ItemInfo> getAllNews() {
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
        return itemInfoDAO.getByItemId(itemId);
    }
}
