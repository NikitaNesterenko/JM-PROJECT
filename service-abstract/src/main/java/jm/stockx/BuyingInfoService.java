package jm.stockx;

import jm.stockx.dto.buyinginfo.BuyingInfoPostDto;
import jm.stockx.entity.BuyingInfo;

public interface BuyingInfoService {

    void create(BuyingInfo buyingInfo);

    Long create(BuyingInfoPostDto buyingInfoPostDto);

    BuyingInfo getBuyingInfoById (Long id);

}
