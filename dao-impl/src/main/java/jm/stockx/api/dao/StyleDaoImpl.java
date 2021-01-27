package jm.stockx.api.dao;

import jm.stockx.dto.style.StyleDto;
import jm.stockx.entity.Style;
import org.springframework.stereotype.Repository;

@Repository
public class StyleDaoImpl extends AbstractDAO<Style, Long> implements StyleDAO {

    @Override
    public StyleDto getStyleDtoByStyleName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.style.StyleDto(" +
                "s.id, " +
                "s.name)" +
                "FROM Style AS s " +
                "WHERE s.name =: name", StyleDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public StyleDto getStyleDtoByStyleId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.style.StyleDto(" +
                "s.id, " +
                "s.name)" +
                "FROM Style AS s " +
                "WHERE s.id =: id", StyleDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // TODO: Использование Entity
    @Override
    public Style getStyleByName(String name) {
        return entityManager.createQuery("" +
                "SELECT s FROM Style AS s WHERE s.name = : styleName", Style.class)
                .setParameter("styleName", name)
                .getSingleResult();
    }


}
