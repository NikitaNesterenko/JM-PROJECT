package jm.stockx;

import jm.stockx.dto.CurrencyDto;
import jm.stockx.entity.Currency;

import java.util.Set;

public interface CurrencyService {

    Set<Currency> getAll();

    CurrencyDto get(Long id);

    void create(Currency currency);

    void update(Currency currency);

    void delete(Long id);

    CurrencyDto getCurrencyByName(String name);

    boolean doesItExistEntity(Long id);

    CurrencyDto getCurrencyDtoById(Long id);
}
