package jm.stockx.api.dao;

import jm.stockx.dto.BuyingInfoDto;
import jm.stockx.entity.BuyingInfo;

public interface BuyingInfoDAO extends GenericDao<BuyingInfo, Long> {
    BuyingInfoDto getBuyingInfoDtoById(Long id);
}
