package jm.stockx.api.dao;

import jm.stockx.dto.SellingInfoDto;
import jm.stockx.entity.SellingInfo;

public interface SellingInfoDAO extends GenericDao<SellingInfo, Long> {
    Double getAverageSalesValue();

    SellingInfoDto getSellingInfoDtoById(Long id);
}
