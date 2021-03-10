package jm.stockx;

import java.math.BigDecimal;
import java.util.List;
import jm.stockx.api.dao.AllItemSalesDAO;
import jm.stockx.dto.allItemSales.AllItemSalesDto;
import jm.stockx.dto.userLocaliseInfo.UserLocaliseInfoDto;
import jm.stockx.entity.Item;
import jm.stockx.enums.UserCurrency;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<AllItemSalesDto> getAllItemSalesByItem(Item item) {
        List<AllItemSalesDto> allItemSalesDtos = this.DAO.getAllItemSalesByItem(item);
        return convertPrice(allItemSalesDtos);

    }

    public List<AllItemSalesDto> getAllItemSalesById(Long id) {
        List<AllItemSalesDto> allItemSalesDtos = this.DAO.getAllItemSalesById(id);
        return convertPrice(allItemSalesDtos);
    }

    private List<AllItemSalesDto> convertPrice(List<AllItemSalesDto> allItemSalesDtos) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserLocaliseInfoDto userLocaliseInfoDto = userLocaliseInfoService.getUserLocaliseInfoDtoByUserName(userName);
        if (userLocaliseInfoDto.getUserCurrency() != UserCurrency.USD) {
            for (AllItemSalesDto a : allItemSalesDtos) {
                BigDecimal amount = a.getPrice().getAmount();
                BigDecimal convertAmount = priceConvertationService.convert(amount,
                        "USD",
                        userLocaliseInfoDto.getUserCurrency().toString());
                a.setPrice(Money.parse(userLocaliseInfoDto.getUserCurrency().toString() + " " + convertAmount));
            }
        }
        return allItemSalesDtos;
    }
}
