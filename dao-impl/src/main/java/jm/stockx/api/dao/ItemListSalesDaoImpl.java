package jm.stockx.api.dao;

import jm.stockx.dto.item.SizesAndPrices;
import jm.stockx.entity.ItemInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemListSalesDaoImpl extends AbstractDAO<ItemInfo, Long> implements ItemListSalesDAO {

    @Override
    public List<SizesAndPrices> getSizesAndPricesByItemId(Long id) {
        return entityManager.createQuery(
                "SELECT NEW jm.stockx.dto.item.SizesAndPrices(info.size.size," +
                        "info.buyingInfo.buyingPrice, " +
                        "info.lowestAsk, " +
                        "info.highestBid) " +
                        "FROM ItemInfo info " +
                        "WHERE info.item.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
