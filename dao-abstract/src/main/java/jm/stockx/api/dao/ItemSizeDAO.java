package jm.stockx.api.dao;

import jm.stockx.dto.ItemSizeDto;
import jm.stockx.dto.shoesize.ShoeSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;

import java.util.List;

public interface ItemSizeDAO extends GenericDao<ItemSize, Long> {
    ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name);

    ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id);

    List<ItemSizeDto> getShoeSizeDtoByShoeSizeType(ItemSizeTypes sizeType);

    ItemSizeDto findOneDtoBySizeName(String itemSize);

    ItemSize findOneBySizeName(String itemSize);
}
