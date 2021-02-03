package jm.stockx.api.dao;

import jm.stockx.dto.buyinginfo.BuyingInfoDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;

public interface BuyingInfoDAO extends GenericDao<BuyingInfo, Long> {

    BuyingInfoDto getBuyingInfoDtoByBuyingInfoId(Long id);

    BuyingInfo getBuyingInfoByItemInfo(ItemInfo itemInfo);
}
