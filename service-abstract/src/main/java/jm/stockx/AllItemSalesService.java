package jm.stockx;

import java.util.List;
import jm.stockx.dto.allitemsales.AllItemSalesDto;
import jm.stockx.entity.Item;

public interface AllItemSalesService {

    List<AllItemSalesDto> getAllItemSalesByItem(Item item);

    List<AllItemSalesDto> getAllItemSalesById(Long id);

}
