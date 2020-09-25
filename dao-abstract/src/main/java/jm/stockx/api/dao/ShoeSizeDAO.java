package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;

public interface ShoeSizeDAO extends GenericDao<ShoeSize, Long> {
    ShoeSizeDto getShoeSizeDtoByName(String name);

    ShoeSizeDto getShoeSizeDtoById(Long id);

//    boolean isExist
}
