package jm.stockx.api.dao;

import jm.stockx.dto.SellerTopInfoDto;
import jm.stockx.dto.SellingInfoDto;
import jm.stockx.entity.SellingInfo;
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
    public SellingInfoDto getSellingInfoDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.SellingInfoDto(" +
                "s.id," +
                "s.orderDate," +
                "s.price" +
                ")" +
                "FROM SellingInfo AS s " +
                "WHERE s.id =: id", SellingInfoDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<SellerTopInfoDto> getTopSellingUsers(){
        String sql = "" +
                "SELECT NEW jm.stockx.dto.SellerTopInfoDto(" +
                "u.id, " +
                "u.username) " +
                "FROM SellingInfo as si " +
                "LEFT JOIN User as u " +
                "ON si.user = u.id " +
                "GROUP BY u.id " +
                "ORDER BY COUNT(si.item) DESC";
        return entityManager.createQuery(sql)
                .setMaxResults(20)
                .getResultList();
    }

    public int getNumberSalesForSpecifiedPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
         List<SellingInfo> list = entityManager.createQuery("" +
                "SELECT si " +
                "FROM SellingInfo as si " +
                "WHERE si.orderDate BETWEEN :begin AND :end", SellingInfo.class)
                .setParameter("begin", beginningPeriod)
                .setParameter("end", endPeriod)
                .getResultList();
        return list.size();
    }
}
