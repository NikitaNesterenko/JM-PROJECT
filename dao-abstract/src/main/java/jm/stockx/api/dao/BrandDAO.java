package jm.stockx.api.dao;

import jm.stockx.dto.brand.BrandDto;
import jm.stockx.entity.Brand;

import java.util.List;

public interface BrandDAO extends GenericDao<Brand, Long> {
    BrandDto getBrandDtoByBrandName(String name);

    BrandDto getBrandDtoByBrandId(Long id);

    BrandDto getBrandByName(String name);

    List<BrandDto> getPopularBrands();

    List<BrandDto> getPopularBrandsInTwoMonths();
}