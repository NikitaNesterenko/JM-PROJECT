package jm.stockx.api.dao;

import jm.stockx.dto.shoeSize.ShoeSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;

import java.util.List;

public interface ItemSizeDAO extends GenericDao<ItemSize, Long> {
    ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name);

    ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id);

    List<ItemSize> getShoeSizeDtoByShoeSizeType(ItemSizeTypes sizeType);

    ItemSize findOneBySizeName(String itemSize);
}
