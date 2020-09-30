package jm.stockx;

import jm.stockx.api.dao.CurrencyDAO;
import jm.stockx.dto.CurrencyDto;
import jm.stockx.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDAO currencyDAO;

    @Autowired
    public CurrencyServiceImpl(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }


    @Override
    public Set<Currency> getAll() {
        return currencyDAO.getAll();
    }

    @Override
    public CurrencyDto get(Long id) {
        return currencyDAO.getCurrencyDtoById(id);
    }

    @Override
    public void create(Currency currency) {
        currencyDAO.add(currency);
    }

    @Override
    public void update(Currency currency) {
        currencyDAO.update(currency);
    }

    @Override
    public void delete(Long id) {
        currencyDAO.deleteById(id);
    }

    @Override
    public CurrencyDto getCurrencyByName(String name) {
        return currencyDAO.getCurrencyDtoByName(name);
    }

    @Override
    public boolean doesItExistEntity(Long id) {
        return currencyDAO.doesItExistEntity(id);
    }

    @Override
    public CurrencyDto getCurrencyDtoById(Long id) {
        return currencyDAO.getCurrencyDtoById(id);
    }
}
