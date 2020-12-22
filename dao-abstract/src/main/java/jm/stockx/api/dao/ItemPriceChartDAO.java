package jm.stockx.api.dao;

import jm.stockx.dto.ItemPriceChartDto;
import jm.stockx.entity.ItemInfo;

public interface ItemPriceChartDAO extends GenericDao<ItemInfo, Long>{
    ItemPriceChartDto get12LatestSales(Long id);
}
