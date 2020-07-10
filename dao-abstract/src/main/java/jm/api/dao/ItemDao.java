package jm.api.dao;

import jm.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    List<Item> getAll();

    Item getById(Long id);

    void add(Item item);

    void deleteById(Long id);

    Item merge(Item item);

    Optional<Item> getItemByName(String name);

}
