package jm.stockx.api.dao;


import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;

import java.util.List;

public interface ItemInfoDAO extends GenericDao<ItemInfo, Long> {

    ItemInfo getItemInfoByItemId(Long itemId);

     ItemInfo getItemInfoByItemName(String itemName);

    List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category);

    List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price);

    SizeInfoDto getItemInfoDtoByIdAndSize(Long itemId, ItemSize itemSize);

    List<ItemInfoCardDto> getAllItemInfoCardDtoForDisplay();
}
