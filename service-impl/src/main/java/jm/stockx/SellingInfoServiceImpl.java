package jm.stockx;

import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.dto.itemInfo.InfoTickerDto;
import jm.stockx.dto.sellingInfo.AverageSalePriceDto;
import jm.stockx.dto.sellingInfo.ItemPriceChangeDto;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.dto.sellingInfo.SellingItemDto;
import jm.stockx.entity.Item;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SellingInfoServiceImpl implements SellingInfoService {
    private final SellingInfoDAO sellingInfoDAO;

    public SellingInfoServiceImpl(SellingInfoDAO sellingInfoDAO) {
        this.sellingInfoDAO = sellingInfoDAO;
    }

    @Override
    public Double getAverageSalesValue() {
        return sellingInfoDAO.getAverageSalesValue();
    }

    @Override
    public List<SellingInfo> getAll() {
        return sellingInfoDAO.getAll();
    }

    @Override
    public SellingInfo get(Long id) {
        return sellingInfoDAO.getById(id);
    }

    @Override
    public void create(SellingInfo sellingInfo) {
        sellingInfoDAO.add(sellingInfo);
    }

    @Override
    public void update(SellingInfo sellingInfo) {
        sellingInfoDAO.update(sellingInfo);
    }

    @Override
    public void delete(Long id) {
        sellingInfoDAO.deleteById(id);
    }

    @Override
    public List<SellerTopInfoDto> getSellerTopInfoDto(){
        return sellingInfoDAO.getSellerTopInfoDto();
    }

    @Override
    public int getCountSalesForPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
        return sellingInfoDAO.getCountSalesForPeriod(beginningPeriod, endPeriod);
    }

    @Override
    public List<ItemTopInfoDto> getItemTopInfoDto(int maxResult) {
        return sellingInfoDAO.getItemTopInfoDto(maxResult);
    }

    @Override
    public ItemPriceChangeDto getPriceChangeByItemId(Long id) {
        List<SellingItemDto> sellingItemDtoList = sellingInfoDAO.getAllSellingItemDtoToCurrentDate(id);

        Money firstPrice = sellingItemDtoList.get(0).getPrice();
        Money lastPrice = sellingItemDtoList.get(sellingItemDtoList.size() - 1).getPrice();

        Money diffMoney = lastPrice.minus(firstPrice);

        String diffPercent = diffMoney.getAmount()
                .divide(firstPrice.getAmount(), 3, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(100))
                .toString() + "%";

        return new ItemPriceChangeDto(diffPercent, diffMoney);
    }


    @Override
    public List<SellingItemDto> getSellingItemDtoByPeriodAndItemId(LocalDateTime begin, LocalDateTime end, Long itemId) {
        return sellingInfoDAO.getSellingItemDtoByPeriodAndItemId(begin, end, itemId);
    }

    @Override
    public List<InfoTickerDto> getInfoTickerDto(ItemCategory itemCategory, LocalDateTime begin, LocalDateTime end, int limit) {
        List<Item> topSellItems = sellingInfoDAO.getTopItemByPeriodAndCategory(begin, end, itemCategory, limit);
        List<InfoTickerDto> infoTickerDtoList = new ArrayList<>();

        for (Item item : topSellItems) {
            List<SellingItemDto> selling = this.getSellingItemDtoByPeriodAndItemId(begin, end, item.getId());

            boolean flag = !selling.get(0).getPrice().isGreaterThan(selling.get(selling.size() - 1).getPrice());
            Money price = selling.get(selling.size() - 1).getPrice();
            String name = reductionName(item.getName());

            infoTickerDtoList.add(new InfoTickerDto(name, price, flag));
        }

        return infoTickerDtoList;
    }

    private String reductionName(String name) {
        StringBuilder reductionName = new StringBuilder();

        for (String s : name.split(" ")) {
            reductionName.append(s.charAt(0));
        }

        return reductionName.toString();
    }

    public AverageSalePriceDto getAverageItemPriceById(Long itemId){
        return sellingInfoDAO.getAverageItemPriceById(itemId);
    }
}
