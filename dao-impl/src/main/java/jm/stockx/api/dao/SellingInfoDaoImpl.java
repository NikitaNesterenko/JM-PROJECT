package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import org.springframework.stereotype.Repository;


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
    public SellingInfo getSellingInfoDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.SellingInfoDto(" +
                "s.id, " +
                "s.orderDate," +
                "s.price)" +
                "FROM SellingInfo AS s " +
                "WHERE id =: id", SellingInfo.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
