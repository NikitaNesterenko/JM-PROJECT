package jm.stockx;

import jm.stockx.dto.ItemCategoryDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;

import java.util.List;

public interface ItemInfoService {

    List<ItemInfo> getAllNews();

    ItemInfo get(Long id);

    void create(ItemInfo itemInfo);

    void update(ItemInfo itemInfo);

    void delete(Long id);

    ItemInfo getItemInfoByItemId(Long ItemId);

    List<ItemCategoryDto> getItemCategoryDtoByCategory(ItemCategory category);
}
