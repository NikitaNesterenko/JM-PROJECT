package jm;

import jm.dto.ItemDto;
import jm.dto.PageDto;

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
}
