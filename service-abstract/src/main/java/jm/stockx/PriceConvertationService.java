package jm.stockx;

import java.math.BigDecimal;

public interface PriceConvertationService {
    BigDecimal convert (BigDecimal price, String toCurrency);
}
