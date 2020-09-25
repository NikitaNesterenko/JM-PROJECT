package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;

public interface ShoeSizeDAO extends GenericDao<ShoeSize, Long> {
    ShoeSizeDto getByName(String name);
    ShoeSizeDto getShoeSizeDtoById(Long id);
}
