package jm.stockx;

import jm.stockx.api.dao.StyleDAO;
import jm.stockx.dto.StyleDto;
import jm.stockx.entity.Style;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StyleServiceImpl implements StyleService {

    private final StyleDAO styleDao;

    public StyleServiceImpl(StyleDAO styleDao) {
        this.styleDao = styleDao;
    }

    @Override
    public List<Style> getAll() {
        return styleDao.getAll();
    }

    @Override
    public StyleDto get(Long id) {
        return styleDao.getStyleDtoById(id);
    }

    @Override
    public StyleDto getStyleByName(String name) {
        return styleDao.getByName(name);
    }

    @Override
    public void create(Style style) {
        styleDao.add(style);
    }

    @Override
    public void update(Style style) {
        styleDao.update(style);
    }

    @Override
    public void delete(Long id) {
        styleDao.deleteById(id);
    }

    @Override
    public boolean isStyleExist(Long id) {
        return styleDao.doesItExistEntity(id);
    }

    @Override
    public StyleDto getStyleDtoById(Long id) {
        return styleDao.getStyleDtoById(id);
    }

    @Override
    public Style getStyle(String name) {
        return styleDao.getStyle(name);
    }

}
