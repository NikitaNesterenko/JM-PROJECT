package jm.stockx.api.dao;


import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.item.ItemDtoAdmin;
import jm.stockx.dto.iteminfo.*;
import jm.stockx.entity.Brand;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemInfoDAO extends GenericDao<ItemInfo, Long> {

    ItemInfo getItemInfoByItemId(Long itemId);

    ItemInfo getItemInfoByItemName(String itemName);

    ItemCategory getItemCategoryByReleaseDate(LocalDate releaseDate);

    ItemInfoDto getItemInfoDtoByItemCategory(ItemCategory itemCategory);

    List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category);

    List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price);

    List<ItemSearchDto> getItemSearchDtoBySearch(String search);

    List<ReleaseItemInfoDto> getReleaseItemDtoByPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);

    void updateItemImageUrl(Long id, String url);

    List<NotReleaseItemInfoDto> getNotReleasedItem();

    List<NotReleaseItemInfoDto> getNotReleasedItemsByBrand(Brand brand);

    List<ItemInfoDto> searchItem(String search, Integer page, Integer size);

    ItemInfoDto getItemInfoDtoByItemName(String name);

    ItemInfoDto getItemInfoDtoByItemId(Long id);

    List<ItemInfoDto> getItemInfoDtoByColor(String itemColors);

    List<ItemInfoDto> getMostPopularItemByBrandName(String name);

    List<ItemInfoDto> getMostPopularItemByStyleId(Long id, int topLimit);

    SizeInfoDto getItemInfoDtoByIdAndSize(Long itemId, ItemSize itemSize);

    List<ItemInfoImageDto> getItemsBuyingYearByUserid(Long id);

    int getCountOfUserBuyingByUserId(Long userId);

    List<ItemInfoDto> getAllItemInfoDto();

    List<ItemInfoDtoDecimal> getAllItemInfoDtoDecimal();

    ItemInfoDtoDecimal getItemInfoDecByItemId(Long id);

    void addItemInfo(Long itemId, ItemDtoAdmin itemDtoAdmin);

}
