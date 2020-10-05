package jm.stockx.converter;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CurrencyConverter {
    private static final String URL_OF_API = "https://www.cbr-xml-daily.ru/daily_json.js";
    //Тут запрос выводит в формате
    // 1 рубль равен
    // "EUR": 0.01092,
    private static final String URL_OF_API_FORMAT_RUB_TO_CURRENCY = "https://www.cbr-xml-daily.ru/latest.js";

    public static Double convertToRub(Double digit, ValuteCharCode valuteCharCode) throws IOException, JSONException {

        if (digit == null) {
            throw new NullPointerException();
        }
        if (digit < 0) {
            throw new IllegalArgumentException();
        }

        Valute tmp = getValuteByCharCode(valuteCharCode);

        return (digit * tmp.getValue()) / tmp.getNominal();
    }

    private static Valute getValuteByCharCode(ValuteCharCode valuteCharCode) throws IOException, JSONException {
        return parseJsonToValute(getValuteJSONObject().getJSONObject(valuteCharCode.name()));
    }

    public static Double convertUSDtoRUB(Double usd) throws IOException, JSONException {
        return convertToRub(usd, ValuteCharCode.USD);
    }

    public static Double convertEURtoRUB(Double eur) throws IOException, JSONException {
        return convertToRub(eur, ValuteCharCode.EUR);
    }

    private static JSONObject getValuteJSONObject() throws IOException, JSONException {
        return readJsonFromUrl().getJSONObject("Valute");
    }

    private static String[] jsonKeysToStringArray() throws JSONException, IOException {
        JSONArray jsonKeys = getValuteJSONObject().names();
        String[] keysArray = new String[jsonKeys.length()];
        for (int i = 0; i < jsonKeys.length(); i++) {
            keysArray[i] = (String) jsonKeys.get(i);
        }
        return keysArray;
    }

    private static Valute parseJsonToValute(JSONObject jsonObject) throws JSONException {
        return new Valute(jsonObject.getString("ID"),
                jsonObject.getInt("NumCode"),
                jsonObject.getString("CharCode"),
                jsonObject.getInt("Nominal"),
                jsonObject.getString("Name"),
                jsonObject.getDouble("Value"),
                jsonObject.getDouble("Previous")
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
