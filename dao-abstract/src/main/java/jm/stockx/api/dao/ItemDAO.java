package jm.stockx.api.dao;

import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.item.ItemDto;
import jm.stockx.dto.item.ReleaseItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ItemSize;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemDAO extends GenericDao<Item, Long> {

    ItemDto getItemDtoByItemName(String name);

    List<ItemDto> searchItem(String search, Integer page, Integer size);

    List<Item> getMostPopularItemByBrandName(String brand);

    List<Item> getMostPopularItemByStyleId(Long styleId, int topLimit);

    List<Item> getNotReleasedItem();

    List<Item> getNotReleasedItemsByBrand(Brand brand);

    ItemDto getItemDtoByItemId(Long id);

    List<ItemDto> getItemDtoByColor(String itemColors);

    Item getItemByName(String name);

    Item getItemById(Long id);

    List<ReleaseItemDto> getReleaseItemDtoByPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);

    void updateItemImageUrl(Long id, String url);

    SizeInfoDto getItemDtoByItemIdandSize(Long itemId, ItemSize size);


}
