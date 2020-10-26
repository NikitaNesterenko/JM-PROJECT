package jm.stockx;

import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.itemInfo.*;
import jm.stockx.dto.page.PageDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemInfoService {

    List<ItemInfo> getAllNews();

    ItemInfo get(Long id);

    void create(ItemInfo itemInfo);

    void update(ItemInfo itemInfo);

    void delete(Long id);

    List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category);

    List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price);

    List<ItemSearchDto> getItemSearchDtoBySearch(String search);

    ItemInfo getItemInfoByItemId(Long itemId);

    ItemInfo getItemInfoByItemName(String itemName);

    List<ReleaseItemInfoDto> getReleaseItemDtoByPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);

    void updateItemImageUrl(Long id, String url);

    List<NotReleaseItemInfoDto> getNotReleasedItem();

    List<NotReleaseItemInfoDto> getNotReleasedItemsByBrand(Brand brand);

    PageDto<ItemInfoDto> searchItem(String search, Integer page, Integer size);

    ItemInfoDto getItemInfoDtoByItemName(String name);

    ItemInfoDto getItemInfoDtoByItemId(Long id);

    List<ItemInfoDto> getItemInfoDtoByColor(String itemColors);

    List<ItemInfoDto> getMostPopularItemByBrandName(String name);

    List<ItemInfoDto> getMostPopularItemByStyleId(Long id, int topLimit);

    SizeInfoDto getItemInfoDtoByIdAndSize(Long itemId, String itemSize);
}
