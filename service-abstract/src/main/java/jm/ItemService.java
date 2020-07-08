package jm;

public interface ItemService extends CrudService<Item> {
    Item getItemByName(String name);
}
