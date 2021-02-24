package jm.stockx;

import jm.stockx.api.dao.StyleDAO;
import jm.stockx.dto.style.StyleDto;
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
    public StyleDto getStyleDtoByStyleId(Long id) {
        return styleDao.getStyleDtoByStyleId(id);
    }

    @Override
    public StyleDto getStyleDtoByStyleName(String name) {
        return styleDao.getStyleDtoByStyleName(name);
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
    public Style getStyleById(Long id) {
       return styleDao.getById(id);
    }

    @Override
    public Style getStyleByName(String name) {
        return styleDao.getStyleByName(name);
    }

}
