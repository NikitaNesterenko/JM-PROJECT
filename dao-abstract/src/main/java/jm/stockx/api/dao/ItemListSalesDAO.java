package jm.stockx.api.dao;

import jm.stockx.dto.item.SizesAndPrices;

import java.util.List;

public interface ItemListSalesDAO {

    List<SizesAndPrices> getSizesAndPricesByItemId(Long id);

}
