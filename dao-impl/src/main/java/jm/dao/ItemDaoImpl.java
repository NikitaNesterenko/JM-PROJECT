package jm.dao;

import jm.Item;
import jm.api.dao.ItemDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item> implements ItemDAO {

    @Override
    public Optional<Item> getItemByName(String name) {
        try {
            Item item = entityManager.createQuery("FROM Item i WHERE i.name = :itemName", Item.class)
                    .setParameter("itemName", name)
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
        } catch (Exception e) {
        }
    }

    @Override
    public byte[] getItemImage(Long id) {
        List<Item> list = entityManager
                .createQuery("FROM Item WHERE id=:id", Item.class)
                .setParameter("id", id)
                .getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0).getItemImage();
    }
}
