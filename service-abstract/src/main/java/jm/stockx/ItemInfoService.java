package jm.stockx;

import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;

import java.util.List;
import java.util.Set;

public interface ItemInfoService {

    List<ItemInfo> getAllNews();

    ItemInfo get(Long id);

    ItemInfo create(ItemInfo itemInfo);

    void update(ItemInfo itemInfo);

    void delete(Long id);

    ItemInfo getItemInfoByItemId(Long ItemId);

    List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category);

    List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price);
}
