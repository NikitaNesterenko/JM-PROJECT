package jm.stockx.api.dao;

import jm.stockx.dto.ItemPriceChartDto;
import jm.stockx.entity.ItemInfo;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemPriceChartDAOImpl extends AbstractDAO<ItemInfo, Long> implements ItemPriceChartDAO {


    @Override
    public ItemPriceChartDto get12LatestSales(Long id) {

        return new ItemPriceChartDto(getBrandName(id), getModelName(id), getPrices(id));

    }

    private String getBrandName(Long id) {
        return (String) entityManager.createQuery("" +
                "SELECT brand.name " +
                "FROM Brand brand " +
                "JOIN ItemInfo info ON brand = info.brand " +
                "JOIN Item item ON info.item = item " +
                "WHERE item.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
    }

    private String getModelName(Long id) {
        return (String) entityManager.createQuery("" +
                "SELECT item.name " +
                "FROM Item item " +
                "WHERE item.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    private BigDecimal[] getPrices(Long id) {

        List<Money> prices = entityManager.createQuery(
                "SELECT buyInfo.buyingPrice " +
                        "FROM BuyingInfo buyInfo " +
                        "JOIN ItemInfo info  ON info.buyingInfo = buyInfo " +
                        "JOIN Item item ON item = info.item " +
                        "WHERE item.id = :id")
                .setParameter("id", id)
                .setMaxResults(12)
                .getResultList();

        BigDecimal[] pricesArr = new BigDecimal[prices.size()];

        for (int i = 0; i < prices.size(); i++) {
            pricesArr[i] = prices.get(i).getAmountMinor();
        }

        return pricesArr;

    }


}
