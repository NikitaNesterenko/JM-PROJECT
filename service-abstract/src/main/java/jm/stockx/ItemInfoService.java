package jm.stockx;

import jm.stockx.dto.ItemCategoryDto;
import jm.stockx.dto.ItemInfoGetDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;

import java.util.List;
import java.util.Set;

public interface ItemInfoService {

    Set<ItemInfo> getAllNews();

    ItemInfo get(Long id);

    void create(ItemInfo itemInfo);

    void update(ItemInfo itemInfo);

    void delete(Long id);

    ItemInfo getItemInfoByItemId(Long ItemId);

    List<ItemCategoryDto> getItemCategoryDtoByCategory(ItemCategory category);

    List<ItemInfoGetDto> getListAndOrderByCash(Integer cash);
}
