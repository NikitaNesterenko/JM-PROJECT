package jm.stockx.api.dao;

import jm.stockx.entity.Currency;

import java.util.Optional;

public interface CurrencyDAO extends GenericDao<Currency, Long> {
    Optional<Currency> getByName(String name);
    boolean doesItExistEntity(Long id);
}
