package jm.stockx;

import jm.stockx.dto.recommendedItem.RecommendedItemDto;
import jm.stockx.enums.ItemCategory;

import java.util.List;

public interface RecommendedItemsService {
    List<RecommendedItemDto> get15RecommendedItems(ItemCategory itemCategory);
}
