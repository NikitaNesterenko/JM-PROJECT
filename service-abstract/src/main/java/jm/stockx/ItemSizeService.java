package jm.stockx;


import jm.stockx.dto.ItemSizeDto;
import jm.stockx.dto.shoesize.ShoeSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;

import java.util.List;
import java.util.Set;

public interface ItemSizeService {

    ItemSizeDto findOneBySizeName(String sizeName);

    Set<ItemSize> getAll();

    ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id);

    void create(ItemSize itemSize);

    void delete(Long id);

    void update(ItemSize itemSize);

    ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name);

    boolean isShoeSizeExist(Long id);

    List<ShoeSizeDto> getShoeSizeDtoByShoeSizeType(ItemSizeTypes sizeType);

    ItemSize getSizeById(Long id);
}

