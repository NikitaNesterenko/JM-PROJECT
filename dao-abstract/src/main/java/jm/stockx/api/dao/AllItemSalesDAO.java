package jm.stockx.api.dao;

import jm.stockx.dto.allitemsales.AllItemSalesDto;
import jm.stockx.dto.item.ItemDto;

import java.util.List;

public interface AllItemSalesDAO {

    List<AllItemSalesDto> getAllItemSalesByItem(ItemDto item);

    List<AllItemSalesDto> getAllItemSalesById(Long itemId);

}
