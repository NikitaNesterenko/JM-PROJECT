package jm.stockx.api.dao;

import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.dto.sellingInfo.SellingInfoDto;
import jm.stockx.dto.sellingInfo.SellingItemDto;
import jm.stockx.entity.Item;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.ItemCategory;
import org.springframework.stereotype.Repository;

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

}
