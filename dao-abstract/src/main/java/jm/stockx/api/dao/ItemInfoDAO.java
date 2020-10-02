package jm.stockx.api.dao;

import jm.stockx.dto.ItemInfoGetDto;
import jm.stockx.entity.ItemInfo;

import java.util.List;

public interface ItemInfoDAO extends GenericDao<ItemInfo, Long>{

    ItemInfo getByItemId(Long itemId);

    List<ItemInfoGetDto> getListAndOrderByCash(Integer cash);
}
