package jm.api.dao;

import jm.Item;

import java.util.Optional;

public interface ItemDao extends CrudDao<Item> {

    Optional<Item> getItemByName(String name);

}
