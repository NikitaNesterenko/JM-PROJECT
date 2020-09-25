package jm.stockx.api.dao;

import jm.stockx.dto.StyleDto;
import jm.stockx.entity.Style;

public interface StyleDAO extends GenericDao<Style, Long> {
    StyleDto getByName(String name);
    StyleDto getStyleDtoById(Long id);

    Style getStyle(String name);
}
