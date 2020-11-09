package jm.stockx.api.dao;

import jm.stockx.dto.buyingInfo.BuyingInfoDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;

import java.util.List;

public interface BuyingInfoDAO extends GenericDao<BuyingInfo, Long> {

    BuyingInfoDto getBuyingInfoDtoByBuyingInfoId(Long id);

    List<BuyingInfo> getBuyingInfosByItemInfo(List<ItemInfo> itemInfos);
}
