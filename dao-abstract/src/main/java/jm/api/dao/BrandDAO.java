package jm.api.dao;

import jm.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandDAO {

    List<Brand> getAll();

    Brand getById(Long id);

    void add(Brand brand);

    void deleteById(Long id);

    Brand merge(Brand brand);

    Optional<Brand> getBrandByName(String name);
}
