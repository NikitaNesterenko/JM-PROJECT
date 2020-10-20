package jm.stockx.api.dao;


import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.dto.itemInfo.ItemSearchDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;

import java.util.List;

public interface ItemInfoDAO extends GenericDao<ItemInfo, Long> {

    ItemInfo getItemInfoByItemId(Long itemId);

     ItemInfo getItemInfoByItemName(String itemName);

    List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category);

    List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price);

    List<ItemSearchDto> getItemSearchDtoBySearch(String search);

}
