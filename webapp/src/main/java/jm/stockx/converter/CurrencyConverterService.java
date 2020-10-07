package jm.stockx.converter;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class CurrencyConverterService {
    /**
     * Ссылки на будущие API для валют
     * URL_OF_API_BASE_RUB = "https://www.cbr-xml-daily.ru/daily_json.js";
     * URL_OF_API_BASE_RUB = "https://www.cbr-xml-daily.ru/latest";
     * <p>
     * URL_OF_API_BASE_USD = "https://api.exchangeratesapi.io/latest?base=USD";
     * URL_OF_API_BASE_EUR = "https://api.exchangeratesapi.io/latest";
     * <p>
     * Тут инстукции для забора JSON за другие даты
     * "https://exchangeratesapi.io/"
     **/

    private final String URL_OF_API_BASE_RUB = "https://www.cbr-xml-daily.ru/daily_json.js";

    public Money convertToRub(Money money) throws Exception {
        if (money.getCurrencyUnit() == CurrencyUnit.of("RUB")) {
            return money;
        } else {

            Currency tmp = getCurrencyByCharCode(money.getCurrencyUnit());

            return (tmp.getValue()
                    .multipliedBy(money.getAmount(), RoundingMode.DOWN)
                    .dividedBy(tmp.getNominal().longValue(), RoundingMode.DOWN));

        }

    }

    private Currency getCurrencyByCharCode(CurrencyUnit currencyUnit) throws Exception {
        return parseJsonToCurrency(getCurrencyJSONObject().getJSONObject(currencyUnit.getCode()));
    }

    private JSONObject getCurrencyJSONObject() throws Exception {
        return getFullJSON().getJSONObject("Valute");
    }

    private Currency parseJsonToCurrency(JSONObject jsonObject) throws JSONException {
        String charCode = jsonObject.getString("CharCode");
        return new Currency(jsonObject.getString("ID"),
                jsonObject.getInt("NumCode"),
                charCode,
                jsonObject.getInt("Nominal"),
                jsonObject.getString("Name"),
                Money.of(CurrencyUnit.of(charCode), jsonObject.getDouble("Value"), RoundingMode.HALF_UP),
                Money.of(CurrencyUnit.of(charCode), jsonObject.getDouble("Previous"), RoundingMode.HALF_UP)
        );
    }

    private JSONObject getFullJSON() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(URL_OF_API_BASE_RUB);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            return new JSONObject(EntityUtils.toString(entity));
        }

    }

}
