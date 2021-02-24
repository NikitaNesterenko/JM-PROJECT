package jm.stockx;

import jm.stockx.dto.currency.CurrencyDto;
import jm.stockx.entity.Currency;

import java.util.Set;

public interface CurrencyService {

    Set<Currency> getAll();

    CurrencyDto getCurrencyDtoByCurrencyId(Long id);

    void create(Currency currency);

    void update(Currency currency);

    void delete(Long id);

    CurrencyDto getCurrencyDtoByCurrencyName(String name);

    boolean doesItExistEntity(Long id);

}
