package jm.dao;

import jm.Item;
import jm.api.dao.ItemDAO;
import jm.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item> implements ItemDAO {

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
    public List<ItemDto> searchItem(String search, Integer page, Integer size) {
        List<ItemDto> foundItems = null;
        try {
            foundItems = entityManager.createQuery("SELECT i FROM Item i  WHERE " +
                    "i.name LIKE '%" + search + "%'", Item.class)
                    .setFirstResult(size * (page - 1) + 1)
                    .setMaxResults(size)
                    .getResultList()
                    .stream()
                    .map(ItemDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.warn("Cannot find items");
        }
        return foundItems;
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
