package jm.stockx;

import jm.stockx.dto.SellerTopInfoDto;
import java.util.List;

public interface SellingInfoService {
    List<SellerTopInfoDto> getTop20SellingUsers();
}