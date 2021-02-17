package jm.stockx;

import jm.stockx.dto.buyingInfo.BuyingInfoPostDto;
import jm.stockx.dto.buyingInfo.BuyingInfoPutDto;
import jm.stockx.entity.Bid;
import jm.stockx.entity.BuyingInfo;

public interface BuyingInfoService {

    void create(BuyingInfo buyingInfo);

    Long create(BuyingInfoPostDto buyingInfoPostDto);

    BuyingInfo getBuyingInfoById (Long id);

}
