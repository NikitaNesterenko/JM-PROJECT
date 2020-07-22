package jm.stockx.api.dao;

import jm.stockx.dto.ItemDto;
import jm.stockx.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {

    void addItemImage(Long id, byte[] array);

    byte[] getItemImage(Long id);

    List<Item> getAll();

    Item getById(Long id);

    void add(Item item);

    void deleteById(Long id);

    Item merge(Item item);

    Optional<Item> getItemByName(String name);

    List<ItemDto> searchItem(String search, Integer page, Integer size);

    List<Item> getTopItemsByStyleFromSellingInfo(Long styleId, int topLimit);
}
