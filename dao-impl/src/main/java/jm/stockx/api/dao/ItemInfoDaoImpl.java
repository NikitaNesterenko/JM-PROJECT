package jm.stockx.api.dao;

import jm.stockx.entity.ItemInfo;
import org.springframework.stereotype.Repository;


@Repository
public class ItemInfoDaoImpl extends AbstractDAO<ItemInfo, Long> implements ItemInfoDAO {

    @Override
    public ItemInfo getByItemInfoId(Long itemId) {
        return entityManager.createQuery(
                     "SELECT i FROM ItemInfo AS i " +
                        "WHERE i.item.id = :itemId", ItemInfo.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }
}