package jm.stockx.api.dao;

import jm.stockx.dto.sellingInfo.AverageSalePriceDto;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.dto.sellingInfo.SellingInfoDto;
import jm.stockx.dto.sellingInfo.SellingItemDto;
import jm.stockx.entity.Item;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class SellingInfoDaoImpl extends AbstractDAO<SellingInfo, Long> implements SellingInfoDAO {

    @Override
    public Double getAverageSalesValue() {
        return (Double) entityManager.createNativeQuery("" +
                "SELECT AVG(si.price)" +
                "FROM selling_info AS si " +
                "WHERE si.order_status = 'DELIVERED'", SellingInfo.class)
                .getSingleResult();
    }

    @Override
    public SellingInfoDto getSellingInfoDtoBySellingInfoId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.sellingInfo.SellingInfoDto(" +
                "s.id," +
                "s.orderDate," +
                "s.price" +
                ")" +
                "FROM SellingInfo AS s " +
                "WHERE s.id =: id", SellingInfoDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<SellerTopInfoDto> getSellerTopInfoDto() {
        String sql = "" +
                "SELECT NEW jm.stockx.dto.sellingInfo.SellerTopInfoDto(" +
                "u.id, " +
                "u.username) " +
                "FROM SellingInfo as si " +
                "LEFT JOIN User as u " +
                "ON si.user.id = u.id " +
                "GROUP BY u.id " +
                "ORDER BY COUNT(si.itemInfo) DESC";
        return entityManager.createQuery(sql, SellerTopInfoDto.class)
                .setMaxResults(20)
                .getResultList();
    }

    @Override
    public int getCountOfSalesByItem(Item item) {
        List<SellingInfo> salesList = entityManager.createQuery("" +
                "SELECT COUNT(si) " +
                "FROM SellingInfo si " +
                "JOIN si.itemInfo i " +
                "WHERE i.item = :item", SellingInfo.class)
                .setParameter("item", item)
                .getResultList();
        return salesList.size();
    }

    @Override
    public double getPriceChangeInPercents(Item item) {
        Money sellingPrice = entityManager.createQuery("" +
                "SELECT si.price " +
                "FROM SellingInfo AS si " +
                "JOIN si.itemInfo i " +
                "WHERE i.item = :item " +
                "ORDER BY si.id DESC", Money.class)
                .setParameter("item", item)
                .getResultList().get(0);

        Money itemPrice = entityManager.createQuery("" +
                "SELECT i.price " +
                "FROM ItemInfo AS i " +
                "WHERE i.item = :item", Money.class)
                .setParameter("item", item)
                .getSingleResult();

        Money priceChange = sellingPrice.minus(itemPrice);

        int itemPriceInt = itemPrice.getAmountMajorInt();
        int priceChangeInt = priceChange.getAmountMajorInt();

        return (double)priceChangeInt * 100.0 / itemPriceInt;
    }

    public int getCountSalesForPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
        List<SellingInfo> list = entityManager.createQuery("" +
                "SELECT si " +
                "FROM SellingInfo as si " +
                "WHERE si.orderDate BETWEEN :begin AND :end", SellingInfo.class)
                .setParameter("begin", beginningPeriod)
                .setParameter("end", endPeriod)
                .getResultList();
        return list.size();
    }

    public List<ItemTopInfoDto> getItemTopInfoDto(int maxResult) {
        String sql = "" +
                "SELECT NEW jm.stockx.dto.sellingInfo.ItemTopInfoDto(" +
                "i.id, " +
                "i.item.name, " +
                "i.itemImageUrl, " +
                "i.lowestAsk, " +
                "COUNT(si.itemInfo)) " +
                "FROM SellingInfo as si " +
                "LEFT JOIN ItemInfo as i " +
                "ON si.itemInfo.id = i.id " +
                "GROUP BY i.id " +
                "ORDER BY COUNT(si.itemInfo) DESC";
        return entityManager.createQuery(sql, ItemTopInfoDto.class)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Override
    public List<SellingItemDto> getSellingItemDtoByPeriodAndItemId(LocalDateTime begin, LocalDateTime end, Long itemId) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.sellingInfo.SellingItemDto( " +
                "si.itemInfo.item, " +
                "si.orderDate, " +
                "si.price " +
                ") " +
                "FROM SellingInfo si " +
                "WHERE si.orderDate >= :begin AND si.orderDate <= :end " +
                "AND si.itemInfo.item.id = :id " +
                "ORDER BY si.orderDate ", SellingItemDto.class)
                .setParameter("begin", begin)
                .setParameter("end", end)
                .setParameter("id", itemId)
                .getResultList();
    }

    @Override
    public List<SellingItemDto> getAllSellingItemDtoToCurrentDate(Long itemId) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.sellingInfo.SellingItemDto( " +
                "si.itemInfo.item, " +
                "si.orderDate, " +
                "si.price " +
                ") " +
                "FROM SellingInfo si " +
                "WHERE si.orderDate <= :date " +
                "AND si.itemInfo.item.id = :id " +
                "ORDER BY si.orderDate ", SellingItemDto.class)
                .setParameter("date", LocalDateTime.now())
                .setParameter("id", itemId)
                .getResultList();
    }

    @Override
    public List<Item> getTopItemByPeriodAndCategory(LocalDateTime beginningPeriod,
                                                    LocalDateTime endPeriod,
                                                    ItemCategory itemCategory,
                                                    int limit) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.entity.Item( " +
                "si.itemInfo.item.id, " +
                "si.itemInfo.item.name " +
                ")  " +
                "FROM SellingInfo si " +
                "WHERE si.orderDate >= :begin AND si.orderDate <= :end " +
                "AND si.itemInfo.itemCategory = :itemCategory " +
                "GROUP BY si.itemInfo.item.id, si.itemInfo.item.name " +
                "ORDER BY COUNT(si.itemInfo.item.id) DESC ", Item.class)
                .setParameter("begin", beginningPeriod)
                .setParameter("end", endPeriod)
                .setParameter("itemCategory", itemCategory)
                .setMaxResults(limit)
                .getResultList();
    }


    @Override
    public AverageSalePriceDto getAverageItemPriceById(Long itemInfoId) {
        BigDecimal averagePrice = (BigDecimal) entityManager.createNativeQuery("" +
                "SELECT ROUND(AVG(selling_info_price)) " +
                "FROM selling_info si " +
                "WHERE si.user_id = ?1")
                .setParameter(1,itemInfoId)
                .getSingleResult();

        BigDecimal currentPrice = (BigDecimal) entityManager.createNativeQuery("" +
                "SELECT ii.item_price " +
                "FROM item_info ii " +
                "WHERE ii.id = ?1")
                .setParameter(1,itemInfoId)
                .getSingleResult();

        return new AverageSalePriceDto(itemInfoId, Money.parse("USD" + (averagePrice)), Money.parse("USD" + (currentPrice)));
    }
}
