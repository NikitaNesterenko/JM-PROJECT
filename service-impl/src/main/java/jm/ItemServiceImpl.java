package jm;

import jm.api.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao) {
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
}
