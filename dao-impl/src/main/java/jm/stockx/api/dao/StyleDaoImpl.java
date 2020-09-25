package jm.stockx.api.dao;

import jm.stockx.dto.StyleDto;
import jm.stockx.entity.Style;
import org.springframework.stereotype.Repository;

@Repository
public class StyleDaoImpl extends AbstractDAO<Style, Long> implements StyleDAO {

    @Override
    public StyleDto getByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.StyleDto(" +
                "s.id, " +
                "s.name)" +
                "FROM Style AS s " +
                "WHERE s.name =: name", StyleDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public StyleDto getStyleDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.StyleDto(" +
                "s.id, " +
                "s.name)" +
                "FROM Style AS s " +
                "WHERE s.id =: id", StyleDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Style getStyle(String name) {
        return entityManager.createQuery("" +
                "FROM Style AS s WHERE s.name = : styleName", Style.class)
                .setParameter("styleName", name)
                .getSingleResult();
    }


}
