package jm.stockx;

import jm.stockx.entity.Brand;
import jm.stockx.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAll();

    Currency get(Long id);

    void create(Currency currency);

    void update(Currency currency);

    void delete(Long id);

    Currency getCurrencyByName(String name);
}
