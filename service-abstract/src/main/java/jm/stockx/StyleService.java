package jm.stockx;

import jm.stockx.dto.style.StyleDto;
import jm.stockx.entity.Style;

import java.util.List;

public interface StyleService {

    List<Style> getAll();

    StyleDto getStyleDtoByStyleId(Long id);

    StyleDto getStyleDtoByStyleName(String name);

    void create(Style style);

    void update(Style style);

    void delete(Long id);

    boolean isStyleExist(Long id);

    Style getStyleById(Long id);

    Style getStyleByName(String name);
}
