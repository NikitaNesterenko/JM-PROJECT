package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import org.springframework.stereotype.Repository;

@Repository
public class SellingDAOImpl extends AbstractDAO<SellingInfo, Long> implements SellingDAO {

    @Override
    public Double getAverageSalesValue() {
        String sql = "SELECT AVG(si.price)" +
                " FROM selling_info as si " +
                " WHERE si.order_status = 'DELIVERED'";

        return (Double) entityManager.createNativeQuery(sql, SellingInfo.class)
                .getSingleResult();
    }
}
