package jm.stockx.api.dao;

import jm.stockx.dto.ItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO extends GenericDao<Item, Long> {

    Optional<Item> getByName(String name);

    void addItemImage(Long id, Byte[] array);

    Byte[] getItemImage(Long id);

    List<ItemDto> searchItem(String search, Integer page, Integer size);

    List<Item> getMostPopularItems(String brand);

    List<Item> getTopItemsByStyleFromSellingInfo(Long styleId, int topLimit);

    List<Item> getNotReleasedItems();

    List<Item> getNotReleasedItemsByBrand(Brand brand);

    List<ItemDto> getItemsByColors(String itemColors);
}
