package jm.stockx;

import jm.stockx.dto.BrandDto;
import jm.stockx.dto.CurrencyDto;
import jm.stockx.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAll();

    Currency get(Long id);

    void create(Currency currency);

    void update(Currency currency);

    void delete(Long id);

    Currency getCurrencyByName(String name);

    boolean doesItExistEntity(Long id);

    CurrencyDto getCurrencyDtoById(Long id);
}
