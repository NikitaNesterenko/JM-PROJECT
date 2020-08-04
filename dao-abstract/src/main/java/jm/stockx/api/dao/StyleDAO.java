package jm.stockx.api.dao;

import jm.stockx.entity.Style;

import java.util.Optional;

public interface StyleDAO extends GenericDao<Style, Long> {
    Optional<Style> getByName(String name);
}
