package jm.stockx.api.dao;

import jm.stockx.dto.CurrencyDto;
import jm.stockx.entity.Currency;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyDaoImpl extends AbstractDAO<Currency, Long> implements CurrencyDAO {

    @Override
    public CurrencyDto getByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.CurrencyDto(" +
                "c.id," +
                "c.name)" +
                "FROM Currency AS c " +
                "WHERE c.name = : name", CurrencyDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public CurrencyDto getCurrencyDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.CurrencyDto(" +
                "c.id," +
                "c.name)" +
                "FROM Currency AS c " +
                "WHERE c.id =: id", CurrencyDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
