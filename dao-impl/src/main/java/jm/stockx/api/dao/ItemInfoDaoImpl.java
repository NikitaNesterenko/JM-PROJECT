package jm.stockx.api.dao;

import jm.stockx.dto.ItemInfoGetDto;
import jm.stockx.entity.ItemInfo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ItemInfoDaoImpl extends AbstractDAO<ItemInfo, Long> implements ItemInfoDAO {

    @Override
    public ItemInfo getByItemId(Long itemId) {
        return entityManager.createQuery(
                        "FROM ItemInfo AS i " +
                        "WHERE i.item.id = :itemId", ItemInfo.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    @Override
    public List<ItemInfoGetDto> getListAndOrderByCash(Integer cash) {
        return entityManager.createQuery("SELECT NEW " +
                "jm.stockx.dto.ItemInfoGetDto(" +
                "if.item.name," +
                "if.item.itemImageUrl," +
                "if.lowestAsk" +
                ")" +
                "FROM ItemInfo if " +
                "WHERE if.price > :cash", ItemInfoGetDto.class)
                .setParameter("cash", cash)
                .getResultList();
    }

}