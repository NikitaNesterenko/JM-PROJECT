package jm.stockx.api.dao;

import jm.stockx.dto.item.ItemDto;
import jm.stockx.entity.Item;

public interface ItemDAO extends GenericDao<Item, Long> {
    ItemDto getItemDtoByItemName(String name);

    ItemDto getItemDtoByItemId(Long id);

    Item getItemByName(String name);

    Item getItemById(Long id);

}
