package jm.dao;

import jm.Item;
import jm.api.dao.ItemDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

    @PersistenceContext
    EntityManager entityManager;

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
