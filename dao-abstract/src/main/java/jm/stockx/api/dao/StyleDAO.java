package jm.stockx.api.dao;

import jm.stockx.entity.Style;

import java.util.List;
import java.util.Optional;

public interface StyleDAO {

    List<Style> getAll();

    Style getById(Long id);

    void add(Style style);

    void deleteById(Long id);

    Style merge(Style style);

    Optional<Style> getStyleByName(String name);

    boolean doesItExistEntity(Long id);
}
