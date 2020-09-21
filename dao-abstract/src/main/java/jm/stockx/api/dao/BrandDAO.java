package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;

public interface BrandDAO extends GenericDao<Brand, Long> {
    BrandDto getBrandDtoByName(String name);

    BrandDto getBrandDtoById(Long id);

    Brand getBrand(String name);
}
