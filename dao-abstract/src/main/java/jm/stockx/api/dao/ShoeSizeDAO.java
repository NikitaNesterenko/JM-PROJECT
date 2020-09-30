package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;

import java.util.List;

import java.util.Optional;

public interface ShoeSizeDAO extends GenericDao<ShoeSize, Long> {
    Optional<ShoeSize> getByName(String name);
    ShoeSizeDto getShoeSizeDtoById(Long id);

    List<ShoeSize> getShoeSizeDtoBySizeType(ShoeSizeTypes sizeType);
}
