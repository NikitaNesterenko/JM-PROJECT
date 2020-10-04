package jm.stockx.api.dao;

import jm.stockx.dto.brand.BrandDto;
import jm.stockx.entity.Brand;

import java.util.List;

public interface BrandDAO extends GenericDao<Brand, Long> {
    BrandDto getBrandDtoByBrandName(String name);

    BrandDto getBrandDtoByBrandId(Long id);

    Brand getBrandByName(String name);

    List<Brand> getPopularBrandIn2Month();


}
