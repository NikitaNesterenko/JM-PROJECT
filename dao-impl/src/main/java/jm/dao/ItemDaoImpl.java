package jm.dao;

import jm.Item;
import jm.api.dao.ItemDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item> implements ItemDao{

    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

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

    @Override
    public void addItemImage(Long id, byte[] array) {
        try {
            entityManager.createQuery("UPDATE Item i SET i.itemImage = :bytesOfImage WHERE id=:id", Item.class)
                    .setParameter("bytesOfImage", array)
                    .setParameter("id", id)
                    .executeUpdate();
            logger.debug("ItemDaoImpl. Изображение товара добавлено в БД");
        } catch (Exception e) {
            logger.debug("ItemDaoImpl. Изображение товара не добавлено в БД");
        }
    }

    @Override
    public byte[] getItemImage(Long id) {
        List<Item> list = entityManager
                .createQuery("FROM Item WHERE id=:id", Item.class)
                .setParameter("id", id)
                .getResultList();
        if (list.size() == 0) {
            logger.debug("ItemDaoImpl. Товар с таким id в БД не найден");
            return null;
        }
        logger.debug("ItemDaoImpl. Товар с таким id в БД найден");
        return list.get(0).getItemImage();
    }
}
