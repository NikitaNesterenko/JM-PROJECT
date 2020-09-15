package jm.stockx;

import jm.stockx.dto.BuyingDto;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import org.joda.money.Money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemService {

    List<Item> getAll();

    Item get(Long id);

    void create(Item item);

    void update(Item item);

    void delete(Long id);

    Item getItemByName(String name);

    PageDto<ItemDto> getPageOfItems(Integer page, String search, Integer size);

    void buyItem(BuyingDto buyingDto);

    List<Item> getTopItemsByStyle(Long styleId, Integer topLimit);

    List<Item> getNotReleasedItems();

    List<Item> getNotReleasedItemsByBrand(Brand brand);

    boolean isItemExist(Long id);

    Map<String, Money> getTheItemDTOForSizeNameBrand(String brand, String name);
}
