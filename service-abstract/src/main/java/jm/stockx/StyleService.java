package jm.stockx;

import jm.stockx.dto.StyleDto;
import jm.stockx.entity.Style;

import java.util.List;

public interface StyleService {

    List<Style> getAll();

    Style get(Long id);

    Style getStyleByName(String name);

    void create(Style style);

    void update(Style style);

    void delete(Long id);

    boolean isStyleExist(Long id);

    StyleDto getStyleDtoById(Long id);

}
