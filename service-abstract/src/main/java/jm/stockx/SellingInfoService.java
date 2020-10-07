package jm.stockx;

import jm.stockx.dto.ItemTopInfoDto;
import jm.stockx.dto.SellerTopInfoDto;
import jm.stockx.entity.SellingInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface SellingInfoService {

    Double getAverageSalesValue();

    Set<SellingInfo> getAll();

    SellingInfo get(Long id);

    void create(SellingInfo sellingInfo);

    void update(SellingInfo sellingInfo);

    void delete(Long id);

    List<SellerTopInfoDto> getTopSellingUsers();

    int getNumberSalesForSpecifiedPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);

    List<ItemTopInfoDto> getItemTopInfoDto(int maxResult);
}
