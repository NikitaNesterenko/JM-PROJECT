package jm.stockx;

import jm.stockx.dto.item.ItemDto;
import jm.stockx.dto.item.ItemSearchDto;
import jm.stockx.dto.page.PageDto;
import jm.stockx.dto.userPortfolio.BuyingDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    Set<Item> getAll();

    ItemDto getItemDtoById(Long id);

    Item create(Item item);

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

    void updateItemImageUrl(Long id, String url);

    List<ItemSearchDto> getItemSearchDtoBySearch(String search);
}
