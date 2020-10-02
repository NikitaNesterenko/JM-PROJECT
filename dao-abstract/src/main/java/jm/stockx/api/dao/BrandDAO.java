package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;

import java.util.List;

public interface BrandDAO extends GenericDao<Brand, Long> {
    BrandDto getBrandDtoByName(String name);

    BrandDto getBrandDtoById(Long id);

    Brand getBrand(String name);

    List<Brand> getPopularBrandIn2Month();


}
