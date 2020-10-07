package jm.stockx;

import jm.stockx.dto.BuyingDto;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    Set<Item> getAll();

    ItemDto getItemDtoById(Long id);

    void create(Item item);

    void update(Item item);

    void delete(Long id);

    ItemDto getItemDtoByName(String name);

    PageDto<ItemDto> getPageOfItems(Integer page, String search, Integer size);

    void buyItem(BuyingDto buyingDto);

    List<Item> getTopItemsByStyle(Long styleId, Integer topLimit);

    List<Item> getNotReleasedItems();

    List<Item> getNotReleasedItemsByBrand(Brand brand);

    boolean isItemExist(Long id);

    Item getItemByName(String name);

    Item getItemById(Long id);

    void updateItemImageUrl(Long id, String url);
}
