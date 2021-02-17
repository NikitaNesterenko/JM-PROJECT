package jm.stockx.api.dao;

import jm.stockx.dto.style.StyleDto;
import jm.stockx.entity.Style;

public interface StyleDAO extends GenericDao<Style, Long> {
    StyleDto getStyleDtoByStyleName(String name);

    StyleDto getStyleDtoByStyleId(Long id);

    StyleDto getStyleByName(String name);
}
