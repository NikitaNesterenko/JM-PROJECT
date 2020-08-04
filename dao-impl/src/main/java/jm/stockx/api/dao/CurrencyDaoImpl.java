package jm.stockx.api.dao;

import jm.stockx.dto.CurrencyDto;
import jm.stockx.entity.Currency;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CurrencyDaoImpl extends AbstractDAO<Currency, Long> implements CurrencyDAO {

    @Override
    public Optional<Currency> getByName(String name) {
        Currency currency = entityManager.createQuery("" +
                "FROM Currency " +
                "WHERE name = :currencyName", Currency.class)
                .setParameter("currencyName", name)
                .getSingleResult();
        return Optional.of(currency);
    }

    @Override
    public CurrencyDto getCurrencyDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT new jm.stockx.dto.CurrencyDto(" +
                "c.id," +
                "c.name)" +
                "FROM Currency AS c " +
                "WHERE c.id =: id", CurrencyDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
