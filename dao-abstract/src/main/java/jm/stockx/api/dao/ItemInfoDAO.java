package jm.stockx.api.dao;


import jm.stockx.dto.ItemCategoryDto;
import jm.stockx.dto.ItemInfoGetDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import java.util.List;

public interface ItemInfoDAO extends GenericDao<ItemInfo, Long> {

    ItemInfo getByItemId(Long itemId);

    List<ItemCategoryDto> getItemCategoryDtoByCategory(ItemCategory category);


    List<ItemInfoGetDto> getListAndOrderByCash(Integer cash);

    ItemInfo getByItemInfoId(Long itemInfoId);
}
