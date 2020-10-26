package jm.stockx.api.dao;

import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.dto.itemInfo.ItemInfoDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ItemInfoDaoImpl extends AbstractDAO<ItemInfo, Long> implements ItemInfoDAO {

    @Override
    public ItemInfo getItemInfoByItemId(Long id) {
        return entityManager.createQuery(
                "SELECT i FROM ItemInfo AS i " +
                        "WHERE i.item.id = :itemId", ItemInfo.class)
                .setParameter("itemId", id)
                .getSingleResult();
    }

    @Override
    public ItemInfo getItemInfoByItemName(String itemName) {
        return entityManager.createQuery(
                "SELECT i FROM ItemInfo AS i " +
                        "WHERE i.item.name = :itemName", ItemInfo.class)
                .setParameter("itemName", itemName)
                .getSingleResult();
    }

    public List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "if.item.name," +
                "if.item.itemImageUrl," +
                "if.lowestAsk" +
                ")" +
                "FROM ItemInfo if " +
                "WHERE if.price > :price", ItemInfoCardDto.class)
                .setParameter("price", price.getAmount())
                .getResultList();
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "i.item.name," +
                "i.item.itemImageUrl," +
                "i.lowestAsk" +
                ")" +
                "FROM ItemInfo AS i " +
                "WHERE i.item.itemCategory =: itemCategory", ItemInfoCardDto.class)
                .setParameter("itemCategory", category)
                .getResultList();
    }

    @Override
    public SizeInfoDto getItemInfoDtoByIdAndSize(Long itemId, ItemSize itemSize) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.SizeInfoDto(" +
                "item_info.lowestAsk," +
                "item_info.highestBid," +
                "item_info.item.name," +
                "item_info.condition," +
                "item_info.buyingInfo.buyingPrice," +
                "item_info.size" +
                ")" +
                "FROM ItemInfo item_info " +
                "WHERE item_info.item.id = :itemId " +
                "AND   item_info.size = :itemSize", SizeInfoDto.class)
                .setParameter("itemId", itemId)
                .setParameter("itemSize", itemSize)
                .setMaxResults(1)
                .getResultList().get(0);
    }

    public List<ItemInfoCardDto> getAllItemInfoCardDtoForDisplay() {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "item_info.item.name," +
                "item_info.itemImageUrl," +
                "item_info.brand.logoImage," +
                "item_info.lowestAsk" +
                ")" +
                "FROM ItemInfo item_info", ItemInfoCardDto.class).getResultList();
    }


}

