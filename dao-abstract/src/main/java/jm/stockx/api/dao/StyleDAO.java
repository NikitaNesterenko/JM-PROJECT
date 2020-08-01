package jm.stockx.api.dao;

import jm.stockx.dto.StyleDto;
import jm.stockx.entity.Style;

import java.util.Optional;

public interface StyleDAO extends GenericDao<Style, Long> {

    Optional<Style> getByName(String name);

    StyleDto getStyleDtoById(Long id);

    boolean doesItExistEntity(Long id);
}
