package jm.stockx;

import jm.stockx.dto.ItemPriceChartDto;

public interface ItemPriceChartService {

    ItemPriceChartDto get12LatestSales(Long id, Long userId);

}
