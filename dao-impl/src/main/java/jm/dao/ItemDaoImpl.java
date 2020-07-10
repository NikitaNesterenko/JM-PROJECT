package jm.dao;

import jm.Item;
import jm.api.dao.ItemDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item> implements ItemDao{

    @Override
    public Optional<Item> getItemByName(String name) {
        try {
            Item item = (Item) entityManager.createNativeQuery("SELECT * FROM items AS i WHERE i.name = :itemname")
                    .setParameter("itemname", name)
                    .getSingleResult();
            return Optional.of(item);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
