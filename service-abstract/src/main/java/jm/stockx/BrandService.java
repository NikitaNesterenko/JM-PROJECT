package jm.stockx;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAll();

    BrandDto get(Long id);

    void create(Brand brand);

    void update(Brand brand);

    void delete(Long id);

    BrandDto getBrandByName(String name);

    boolean isBrandExist(Long id);

    BrandDto getBrandDtoById(Long id);

    Brand getBrand(String name);
}
