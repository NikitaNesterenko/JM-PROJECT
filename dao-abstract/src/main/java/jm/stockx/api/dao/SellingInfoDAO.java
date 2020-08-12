package jm.stockx.api.dao;

import jm.stockx.dto.SellerTopInfoDto;
import jm.stockx.dto.SellingInfoDto;
import jm.stockx.entity.SellingInfo;

import java.util.List;

public interface SellingInfoDAO extends GenericDao<SellingInfo, Long> {
    Double getAverageSalesValue();

    SellingInfoDto getSellingInfoDtoById(Long id);

    List<SellerTopInfoDto> getTopSellingUsers();
}
