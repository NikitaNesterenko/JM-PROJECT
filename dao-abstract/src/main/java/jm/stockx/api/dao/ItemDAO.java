package jm.stockx.api.dao;

import jm.stockx.dto.ItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import jm.stockx.enums.ItemDirection;

import java.util.List;
import java.util.Map;

public interface ItemDAO extends GenericDao<Item, Long> {

    ItemDto getItemDtoByName(String name);

    List<ItemDto> searchItem(String search, Integer page, Integer size);

    List<Item> getMostPopularItems(String brand);

    List<Item> getTopItemsByStyleFromSellingInfo(Long styleId, int topLimit);

    List<Item> getNotReleasedItems();

    List<Item> getNotReleasedItemsByBrand(Brand brand);

    ItemDto getItemDtoById(Long id);

    List<ItemDto> getItemsByColors(String itemColors);

    Item getItemByName(String name);

    Item getItemById(Long id);

    void updateItemImageUrl(Long id, String url);

    List<Item> searchItem(String search);

    Long getCountItemByItemDirection(String search, ItemDirection itemDirection);

    List<ItemDirection> getItemDirection(String name);

    Map<ItemDirection, Long> getMap(String search);
}
