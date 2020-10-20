package jm.stockx.api.dao;

import jm.stockx.dto.item.ItemDto;
import jm.stockx.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item, Long> implements ItemDAO {

    @Override
    public ItemDto getItemDtoByItemName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.item.ItemDto(" +
                "i.id, " +
                "i.name " +
                ")" +
                "FROM Item AS i " +
                "WHERE i.name = :name", ItemDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public ItemDto getItemDtoByItemId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.item.ItemDto(" +
                "i.id, " +
                "i.name " +
                ")" +
                "FROM Item AS i " +
                "WHERE i.id =: id", ItemDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Item getItemByName(String name) {
        return entityManager.createQuery("" +
                "SELECT i FROM Item i " +
                "WHERE i.name = : name ", Item.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Item getItemById(Long id) {
        return entityManager.createQuery("" +
                "SELECT i FROM Item i " +
                "WHERE i.id = : id ", Item.class)
                .setParameter("id", id)
                .getSingleResult();
    }


//    @Override
//    public List<Item> getMostPopularItemByBrandName(String name) {
//        return entityManager.createQuery("" +
//                "SELECT i FROM Item i " +
//                "INNER JOIN BuyingInfo AS bi " +
//                "ON i.id = bi.id " +
//                "INNER JOIN Brand AS b " +
//                "WHERE b.name =:name " +
//                "ORDER BY COUNT(i.id) DESC", Item.class)
//                .setParameter("name", name)
//                .setMaxResults(10)
//                .getResultList();
//    }
//
//    @Override
//    public List<Item> getMostPopularItemByStyleId(Long id, int topLimit) {
//        return entityManager.createQuery("" +
//                "SELECT si FROM SellingInfo si " +
//                "LEFT JOIN Item AS i " +
//                "ON si.id=i.id " +
//                "WHERE i.style = :styleId " +
//                "GROUP BY si.id " +
//                "ORDER BY count(si.id) DESC", Item.class)
//                .setParameter("styleId", id)
//                .setMaxResults(topLimit)
//                .getResultList();
//    }
}
