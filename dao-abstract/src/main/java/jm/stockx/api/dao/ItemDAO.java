package jm.stockx.api.dao;

import jm.stockx.dto.ItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import org.joda.money.Money;

import java.util.List;

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

    ItemDto getItemDtoBySizeInfo(Double size, Money retailPrice);
}
