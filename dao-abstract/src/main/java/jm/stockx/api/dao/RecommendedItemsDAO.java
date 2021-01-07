package jm.stockx.api.dao;

import jm.stockx.dto.recommendedItem.RecommendedItemDto;
import jm.stockx.enums.ItemCategory;

import java.util.List;

public interface RecommendedItemsDAO {

    List<RecommendedItemDto> get15RecommendedItems( ItemCategory itemCategory);

}
