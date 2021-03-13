package jm.stockx.api.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jm.stockx.dto.sellingInfo.AverageSalePriceDto;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.dto.sellingInfo.SellingCountDto;
import jm.stockx.dto.sellingInfo.SellingInfoDto;
import jm.stockx.dto.sellingInfo.SellingItemDto;
import jm.stockx.dto.sellingInfo.UserWithMostSalesDto;
import jm.stockx.entity.Item;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;


@Repository
public class SellingInfoDaoImpl extends AbstractDAO<SellingInfo, Long> implements SellingInfoDAO {

    // TODO: Использование Entity
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

    // TODO: Использование Entity
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
                "GROUP BY 1, 2, 3, 4 " +
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

    // TODO: Использование Entity
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

    @Override
    public List<SellingCountDto> getSellingCountDtoLastYear(Long itemId) {

        LocalDate today = LocalDate.now(); // дата сегодня

        LocalDate oneYearAgo = today.minusYears(1L); // дата год назад

        List<LocalDate> oneMonthStepDates = oneYearAgo.datesUntil(today, Period.ofMonths(1))
                .collect(Collectors.toList()); // список дат с шагом в один месяц

        List<SellingCountDto> sellingCountDtoList = new ArrayList<>(); // этот список будет заполняться нужными нам DTO

        for (LocalDate date : oneMonthStepDates) {

            LocalDate firstDayOfMonth = date.withDayOfMonth(1); // первый день месяца

            LocalDateTime start = firstDayOfMonth.atStartOfDay(); // начало первого дня месяца

            LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth()); // последний день месяца

            LocalDateTime end = lastDayOfMonth.atTime(23,59, 59); // конец последнего дня месяца

            long count = entityManager.createQuery("" +
                    "SELECT COUNT(si.id) " +
                    "FROM SellingInfo si " +
                    "JOIN si.itemInfo i " +
                    "WHERE i.id = :itemId " +
                    "AND si.orderDate <= : end " +
                    "AND si.orderDate >= : start", Long.class)
                    .setParameter("itemId", itemId)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getSingleResult();

            sellingCountDtoList.add(new SellingCountDto(itemId, date.getMonth(), count));
        }

        return sellingCountDtoList;
    }

    @Override
    public int getCountOfUserSalesByUserId(Long userId) {
        return entityManager.createQuery("" +
                "SELECT COUNT(si.user.id) " +
                "FROM SellingInfo si " +
                "WHERE si.user.id = :userId " +
                "AND NOT si.status = 'CANCELED'", Integer.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<UserWithMostSalesDto> getUsersWithMostSales() {
        String sql = "" +
            "SELECT NEW jm.stockx.dto.sellingInfo.UserWithMostSalesDto(" +
            "u.id, " +
            "CONCAT(u.firstName, ' ', u.lastName), " +
            "COUNT(si.user)) " +
            "FROM SellingInfo as si " +
            "LEFT JOIN User as u " +
            "ON si.user.id = u.id " +
            "GROUP BY u.id " +
            "ORDER BY COUNT(si.itemInfo) DESC";

        return entityManager.createQuery(sql, UserWithMostSalesDto.class)
            .setMaxResults(20)
            .getResultList();
    }
}
