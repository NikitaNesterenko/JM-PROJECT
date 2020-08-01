package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;

public interface SellingInfoDAO extends GenericDao<SellingInfo, Long> {
    SellingInfo getSellingInfoDtoById(Long id);
}
