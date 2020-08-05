package jm.stockx;

import jm.stockx.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAll();

    Brand get(Long id);

    void create(Brand brand);

    void update(Brand brand);

    void delete(Long id);

    Brand getBrandByName(String name);

    boolean isBrandExist(Long id);

}
