package jm.api.dao;

public interface ItemDAO {

    void addItemImage(Long id, byte[] array);

    byte[] getItemImage(Long id);
}
