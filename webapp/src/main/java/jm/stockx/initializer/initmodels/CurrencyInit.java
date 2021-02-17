package jm.stockx.initializer.initmodels;

import jm.stockx.CurrencyService;
import jm.stockx.entity.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания валют в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class CurrencyInit {
    private final CurrencyService currencyService;

    public void initializeCurrencies() {
        List<Currency> currencyListListForCreation = createCurrenciesForInitialization();
        currencyListListForCreation.forEach(currencyService::create);
    }

    private List<Currency> createCurrenciesForInitialization() {
        return Arrays.asList(
                new Currency("USD"),
                new Currency("EUR"),
                new Currency("RUB"));
    }
}