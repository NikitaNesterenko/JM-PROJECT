package jm.stockx.api.dao;

import jm.stockx.dto.CurrencyDto;
import jm.stockx.entity.Currency;

public interface CurrencyDAO extends GenericDao<Currency, Long> {
    CurrencyDto getByName(String name);
    CurrencyDto getCurrencyDtoById(Long id);
}
