package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import org.springframework.stereotype.Repository;


@Repository
public class SellingInfoDaoImpl extends AbstractDAO<SellingInfo, Long> implements SellingInfoDAO {


    @Override
    public SellingInfo getSellingInfoDtoById(Long id) {
        return entityManager.createQuery(
            "SELECT new jm.stockx.dto.SellingInfoDto(" +
               "s.id, " +
               "s.name)" +
               "FROM SellingInfo AS s " +
               "WHERE id =: id", SellingInfo.class)
               .setParameter("id", id)
               .getSingleResult();
    }

    @Override
    public Double getAverageSalesValue() {
        return (Double) entityManager.createNativeQuery(
             "SELECT AVG(si.price)" +
                " FROM selling_info as si " +
                " WHERE si.order_status = 'DELIVERED'", SellingInfo.class)
                .getSingleResult();
    }
}
