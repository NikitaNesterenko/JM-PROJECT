package jm.stockx.api.dao;

import jm.stockx.dto.buyingInfo.BuyingInfoDto;
import jm.stockx.entity.BuyingInfo;

public interface BuyingInfoDAO extends GenericDao<BuyingInfo, Long> {
    BuyingInfoDto getBuyingInfoDtoByBuyingInfoId(Long id);
}
