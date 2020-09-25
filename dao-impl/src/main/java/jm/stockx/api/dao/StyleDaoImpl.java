package jm.stockx.api.dao;

import jm.stockx.dto.StyleDto;
import jm.stockx.entity.Style;
import org.springframework.stereotype.Repository;

@Repository
public class StyleDaoImpl extends AbstractDAO<Style, Long> implements StyleDAO {

    @Override
    public StyleDto getByName(String name) {
        Style style = entityManager.createQuery("" +
                "FROM Style " +
                "WHERE name = :name", Style.class)
                .setParameter("name", name)
                .getSingleResult();
        return new StyleDto(style);
    }

    @Override
    public StyleDto getStyleDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.StyleDto(" +
                "s.id, " +
                "s.name)" +
                "FROM Style AS s " +
                "WHERE id =: id", StyleDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
