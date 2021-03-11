package jm.stockx;

import java.math.BigDecimal;
import java.util.List;
import jm.stockx.api.dao.AllItemSalesDAO;
import jm.stockx.dto.allItemSales.AllItemSalesDto;
import jm.stockx.entity.Item;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllItemSalesServiceImpl implements AllItemSalesService {

    private final AllItemSalesDAO DAO;
    private final UserLocaliseInfoService userLocaliseInfoService;
    private final PriceConvertationService priceConvertationService;

    @Autowired
    public AllItemSalesServiceImpl(AllItemSalesDAO dao, UserLocaliseInfoService userLocaliseInfoService, PriceConvertationService priceConvertationService) {
        this.DAO = dao;
        this.userLocaliseInfoService = userLocaliseInfoService;
        this.priceConvertationService = priceConvertationService;
    }

    public List<AllItemSalesDto> getAllItemSalesByItem(Item item, Long userId) {
        List<AllItemSalesDto> allItemSalesDtos = this.DAO.getAllItemSalesByItem(item);
        String moneyTag = userLocaliseInfoService.getMoneyTagByUserId(userId);
        if (moneyTag != "USD") {
            return convertPrice(allItemSalesDtos, moneyTag);
        }
        return allItemSalesDtos;
    }

    public List<AllItemSalesDto> getAllItemSalesById(Long id, Long userId) {
        List<AllItemSalesDto> allItemSalesDtos = this.DAO.getAllItemSalesById(id);
        String moneyTag = userLocaliseInfoService.getMoneyTagByUserId(userId);
        if (moneyTag != "USD") {
            return convertPrice(allItemSalesDtos, moneyTag);
        }
        return allItemSalesDtos;
    }

    private List<AllItemSalesDto> convertPrice(List<AllItemSalesDto> allItemSalesDtos, String moneyTag) {
        BigDecimal course = priceConvertationService.convert(BigDecimal.valueOf(1L), moneyTag);
            for (AllItemSalesDto a : allItemSalesDtos) {
                BigDecimal amount = a.getPrice().getAmount();
                a.setPrice(Money.parse(moneyTag + " " + amount.multiply(course)));
            }
        return allItemSalesDtos;
    }
}
