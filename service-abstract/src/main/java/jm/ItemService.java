package jm;

public interface ItemService {

    void addItemImage(Long id, byte[] array);

    byte[] getItemImage(Long id);
}
