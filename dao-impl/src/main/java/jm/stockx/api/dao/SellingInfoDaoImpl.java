package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import org.springframework.stereotype.Repository;


@Repository
public class SellingInfoDaoImpl extends AbstractDAO<SellingInfo, Long> implements SellingInfoDAO {


    @Override
    public SellingInfo getSellingInfoDtoById(Long id) {
        return entityManager.createQuery(
            "SELECT new jm.stockx.dto.SellingInfoDto(s.id, s.name)" +
               "FROM SellingInfo AS s " +
               "WHERE id =: id", SellingInfo.class)
               .setParameter("id", id).getSingleResult();
    }
}
