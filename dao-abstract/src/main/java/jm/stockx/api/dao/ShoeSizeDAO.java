package jm.stockx.api.dao;

import jm.stockx.dto.shoeSize.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;

import java.util.List;

public interface ShoeSizeDAO extends GenericDao<ShoeSize, Long> {
    ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name);

    ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id);

    List<ShoeSize> getShoeSizeDtoByShoeSizeType(ShoeSizeTypes sizeType);
}
