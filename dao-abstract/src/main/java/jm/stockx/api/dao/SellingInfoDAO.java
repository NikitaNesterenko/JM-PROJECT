package jm.stockx.api.dao;

import jm.stockx.dto.SellerTopInfoDto;
import jm.stockx.dto.SellingInfoDto;
import jm.stockx.entity.SellingInfo;

import java.time.LocalDateTime;
import java.util.Set;

public interface SellingInfoDAO extends GenericDao<SellingInfo, Long> {
    Double getAverageSalesValue();

    SellingInfoDto getSellingInfoDtoById(Long id);

    Set<SellerTopInfoDto> getTopSellingUsers();

    int getNumberSalesForSpecifiedPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod);
}
