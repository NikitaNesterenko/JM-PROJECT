package jm.stockx.api.dao;

import jm.stockx.dto.currency.CurrencyDto;
import jm.stockx.entity.Currency;

public interface CurrencyDAO extends GenericDao<Currency, Long> {
    CurrencyDto getCurrencyDtoByCurrencyName(String name);

    CurrencyDto getCurrencyDtoByCurrencyId(Long id);
}
