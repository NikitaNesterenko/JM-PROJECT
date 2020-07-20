package jm.stockx;

import jm.stockx.dto.BuyingDto;
import jm.stockx.entity.Item;

import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;

import java.util.List;

public interface ItemService {

    List<Item> getAll();

    Item get(Long id);

    void create(Item item);

    void update(Item item);

    void delete(Long id);

    Item getItemByName(String name);

    void addItemImage(Long id, byte[] array);

    byte[] getItemImage(Long id);

    PageDto<ItemDto> getPageOfItems(Integer page, String search, Integer size);

    boolean buyItem(BuyingDto buyingDto);
}
