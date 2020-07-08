package jm;

import jm.api.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemdao;

    @Autowired
    public ItemServiceImpl(ItemDao itemdao) {
        this.itemdao = itemdao;
    }

    @Override
    public Item getItemByName(String name) {
        return itemdao.getItemByName(name).get();
    }

    @Override
    public List<Item> getAll() {
        return itemdao.getAll();
    }

    @Override
    public Item get(Long id) {
        return itemdao.getById(id);
    }

    @Transactional
    @Override
    public void create(Item item) {
        itemdao.add(item);
    }

    @Transactional
    @Override
    public void update(Item item) {
        itemdao.merge(item);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        itemdao.deleteById(id);
    }
}
