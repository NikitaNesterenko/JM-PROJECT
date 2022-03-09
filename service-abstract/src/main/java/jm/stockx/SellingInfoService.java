package jm.stockx;

import jm.stockx.dto.itemInfo.InfoTickerDto;
import jm.stockx.dto.sellingInfo.AverageSalePriceDto;
import jm.stockx.dto.sellingInfo.ItemPriceChangeDto;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.dto.sellingInfo.SellingItemDto;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.ItemCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface SellingInfoService {

    Double getAverageSalesValue();

    Double getMinSalesValue(Long id);

    Double getMaxSalesValue(Long id);

    List<SellingInfo> getAll();

    SellingInfo get(Long id);

    void create(SellingInfo sellingInfo);

    void update(SellingInfo sellingInfo);

    void delete(Long id);

    List<SellerTopInfoDto> getSellerTopInfoDto();

    int getCountSalesForPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);

    List<ItemTopInfoDto> getItemTopInfoDto(int maxResult);

    ItemPriceChangeDto getPriceChangeByItemId(Long id);

    List<SellingItemDto> getSellingItemDtoByPeriodAndItemId(LocalDateTime begin, LocalDateTime end, Long itemId);

    List<InfoTickerDto> getInfoTickerDto(ItemCategory itemCategory, LocalDateTime begin, LocalDateTime end, int limit);

    AverageSalePriceDto getAverageItemPriceById(Long itemId);


}
