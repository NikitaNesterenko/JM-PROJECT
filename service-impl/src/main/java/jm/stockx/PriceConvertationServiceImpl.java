package jm.stockx;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PriceConvertationServiceImpl implements PriceConvertationService{

    @Override
    public BigDecimal convert(BigDecimal price, String fromCurrency, String toCurrency) {
        String request = "https://api.coingate.com/v2/rates/merchant/" + fromCurrency + "/" + toCurrency;
        String result = "-1";
        HttpGet httpRequest = new HttpGet(request);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.equals("-1")) {
            return price;
        }
        return price.multiply(BigDecimal.valueOf(Double.parseDouble(result)));
    }
}
