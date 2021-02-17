package jm.stockx;

import jm.stockx.dto.allitemsales.AllItemSalesDto;
import jm.stockx.dto.item.ItemDto;

import java.util.List;

public interface AllItemSalesService {

    List<AllItemSalesDto> getAllItemSalesByItem(ItemDto item);

    List<AllItemSalesDto> getAllItemSalesById(Long id);

}
