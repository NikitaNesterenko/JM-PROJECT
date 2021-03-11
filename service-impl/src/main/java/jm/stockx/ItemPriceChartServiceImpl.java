package jm.stockx;

import jm.stockx.api.dao.ItemPriceChartDAO;
import jm.stockx.dto.ItemPriceChartDto;
import jm.stockx.dto.allItemSales.AllItemSalesDto;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class ItemPriceChartServiceImpl implements ItemPriceChartService {

    private final ItemPriceChartDAO itemPriceChartDAO;
    private final UserLocaliseInfoService userLocaliseInfoService;
    private final PriceConvertationService priceConvertationService;

    @Autowired
    public ItemPriceChartServiceImpl(ItemPriceChartDAO itemPriceChartDAO, UserLocaliseInfoService userLocaliseInfoService, PriceConvertationService priceConvertationService) {

        this.itemPriceChartDAO = itemPriceChartDAO;
        this.userLocaliseInfoService = userLocaliseInfoService;
        this.priceConvertationService = priceConvertationService;
    }

    @Override
    public ItemPriceChartDto get12LatestSales(Long id, Long userId) {
        ItemPriceChartDto itemPriceChartDto = itemPriceChartDAO.get12LatestSales(id);
        String moneyTag = userLocaliseInfoService.getMoneyTagByUserId(userId);
        if (moneyTag != "USD") {
            return convertPrice(itemPriceChartDto, moneyTag);
        }
        return itemPriceChartDto;
    }

    private ItemPriceChartDto convertPrice(ItemPriceChartDto itemPriceChartDto, String moneyTag) {
        BigDecimal course = priceConvertationService.convert(BigDecimal.valueOf(1L), moneyTag);
        System.out.println(course);
        BigDecimal[] prices = itemPriceChartDto.getPrices();
        prices = Arrays.stream(prices).map((p)->p.multiply(course)).toArray(BigDecimal[]::new);
        itemPriceChartDto.setPrices(prices);
        return itemPriceChartDto;
    }

}
