package jm.stockx.api.dao;

import jm.stockx.entity.Brand;

import java.util.Optional;

public interface BrandDAO extends GenericDao<Brand, Long> {
    Optional<Brand> getByName(String name);
}
