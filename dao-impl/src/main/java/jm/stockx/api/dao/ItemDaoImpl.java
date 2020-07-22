package jm.stockx.api.dao;

import jm.api.dao.ItemDAO;
import jm.stockx.dto.ItemDto;
import jm.stockx.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ItemDto> searchItem(String search, Integer page, Integer size) {
        List<ItemDto> foundItems = entityManager.createQuery("SELECT i FROM Item i  WHERE " +
                "i.name LIKE '%" + search + "%'", Item.class)
                .setFirstResult(size * (page - 1) + 1)
                .setMaxResults(size)
                .getResultList()
                .stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
        return foundItems;
    }

    @Override
    public List<Item> getMostPopularItems(String brand) {
        List<Item> itemList =
                entityManager.createNativeQuery("SELECT COUNT(i.id) AS i_count" +
                        " FROM items as i " +
                        "INNER JOIN buying_item bi on i.id = bi.item_id INNER JOIN brand AS b WHERE b.name =:brand ORDER BY i_count")
                        .setParameter("brand", brand)
                        .setMaxResults(10)
                        .getResultList();
        return itemList;
    }

    @Override
    public void addItemImage(Long id, Byte[] array) {
        try {
            entityManager.createQuery("UPDATE Item i SET i.itemImage = :bytesOfImage WHERE id=:id", Item.class)
                    .setParameter("bytesOfImage", array)
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public Byte[] getItemImage(Long id) {
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
