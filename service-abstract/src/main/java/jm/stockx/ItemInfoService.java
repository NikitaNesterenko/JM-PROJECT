package jm.stockx;

import jm.stockx.entity.ItemInfo;

import java.util.Set;

public interface ItemInfoService {

    Set<ItemInfo> getAllNews();

    ItemInfo get(Long id);

    void create(ItemInfo itemInfo);

    void update(ItemInfo itemInfo);

    void delete(Long id);

    ItemInfo getItemInfoByItemId(Long ItemId);
}
