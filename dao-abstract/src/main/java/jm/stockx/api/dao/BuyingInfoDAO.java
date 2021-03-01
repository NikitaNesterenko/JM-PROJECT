package jm.stockx.api.dao;

import jm.stockx.dto.buyinginfo.BuyingInfoDto;
import jm.stockx.dto.buyinginfo.BuyingInfoPostDto;
import jm.stockx.dto.userportfolio.BuyingDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;

public interface BuyingInfoDAO extends GenericDao<BuyingInfo, Long> {

    void addBuyingInfo(BuyingDto buyingDto);

    Long addBuyingInfo(BuyingInfoPostDto buyingInfoPostDto);

    BuyingInfoDto getBuyingInfoDtoByBuyingInfoId(Long id);

    BuyingInfoDto getBuyingInfoDtoByItemInfo(ItemInfo itemInfo);
}
