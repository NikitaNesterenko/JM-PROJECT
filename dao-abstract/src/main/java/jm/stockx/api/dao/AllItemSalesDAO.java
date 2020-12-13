package jm.stockx.api.dao;

import java.util.List;

import jm.stockx.dto.allItemSales.AllItemSalesDto;
import jm.stockx.entity.Item;

public interface AllItemSalesDAO {

    List<AllItemSalesDto> getAllItemSalesByItem(Item item);

    List<AllItemSalesDto> getAllItemSalesById(Long itemId);

}
