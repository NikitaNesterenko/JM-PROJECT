package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;

import java.util.Set;

public interface ShoeSizeDAO extends GenericDao<ShoeSize, Long> {
    ShoeSizeDto getShoeSizeDtoByName(String name);

    ShoeSizeDto getShoeSizeDtoById(Long id);
    Set<ShoeSize> getShoeSizeDtoBySizeType(ShoeSizeTypes sizeType);
}
