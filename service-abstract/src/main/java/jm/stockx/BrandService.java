package jm.stockx;

import jm.stockx.dto.brand.BrandDto;
import jm.stockx.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAll();

    BrandDto get(Long id);

    void create(Brand brand);

    void update(Brand brand);

    void delete(Long id);

    BrandDto getBrandDtoByBrandName(String name);

    boolean isBrandExist(Long id);

    BrandDto getBrandDtoByBrandId(Long id);

    Brand getBrandByName(String name);

    List<BrandDto> getPopularBrands();

    List<BrandDto> getPopularBrandsInTwoMonths();

    Brand getBrandById(Long id);
}