package jm.stockx.converter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CurrencyConverter {
    private static final String URL_OF_API = "https://www.cbr-xml-daily.ru/daily_json.js";

    public static Money convertToRub(BigDecimal digit, CurrencyUnit currencyUnit) throws IOException, JSONException {

        if (digit == null) {
            throw new NullPointerException();
        }
        if (digit.doubleValue() < 0) {
            throw new IllegalArgumentException();
        }

        Valute tmp = getValuteByCharCode(currencyUnit);

        return (tmp.getValue().multipliedBy(digit, RoundingMode.DOWN).dividedBy(tmp.getNominal().longValue(), RoundingMode.DOWN));
    }

    private static Valute getValuteByCharCode(CurrencyUnit currencyUnit) throws IOException, JSONException {
        return parseJsonToValute(getValuteJSONObject().getJSONObject(currencyUnit.getCode()));
    }

    public static Money convertUSDtoRUB(BigDecimal usd) throws IOException, JSONException {
        return convertToRub(usd, CurrencyUnit.of("USD"));
    }

    public static Money convertEURtoRUB(BigDecimal eur) throws IOException, JSONException {
        return convertToRub(eur, CurrencyUnit.of("EUR"));
    }

    private static JSONObject getValuteJSONObject() throws IOException, JSONException {
        return readJsonFromUrl().getJSONObject("Valute");
    }

    private static Valute parseJsonToValute(JSONObject jsonObject) throws JSONException {
        return new Valute(jsonObject.getString("ID"),
                jsonObject.getInt("NumCode"),
                jsonObject.getString("CharCode"),
                jsonObject.getInt("Nominal"),
                jsonObject.getString("Name"),
                Money.of(CurrencyUnit.of("RUB"), jsonObject.getDouble("Value"), RoundingMode.HALF_UP),
                Money.of(CurrencyUnit.of("RUB"), jsonObject.getDouble("Previous"), RoundingMode.HALF_UP)
        );
    }

    private static JSONObject readJsonFromUrl() throws IOException, JSONException {
        try (InputStream is = new URL(CurrencyConverter.URL_OF_API).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
