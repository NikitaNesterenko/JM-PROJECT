package jm.stockx.api.dao;

import jm.stockx.entity.ItemInfo;

public interface ItemInfoDAO extends GenericDao<ItemInfo, Long>{

    ItemInfo getByItemId(Long itemId);
}
