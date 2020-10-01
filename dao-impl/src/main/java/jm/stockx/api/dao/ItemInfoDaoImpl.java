package jm.stockx.api.dao;

import jm.stockx.dto.ItemCategoryDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
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
    public List<ItemCategoryDto> getItemCategoryDtoByCategory(ItemCategory category) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemCategoryDto(" +
                "i.item.name," +
                "i.item.itemImageUrl," +
                "i.lowestAsk" +
                ")" +
                "FROM ItemInfo AS i " +
                "WHERE i.item.itemCategory =: itemCategory", ItemCategoryDto.class)
                .setParameter("itemCategory", category)
                .getResultList();
    }

}