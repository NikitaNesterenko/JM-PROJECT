package jm.stockx;

import jm.stockx.dto.SellerTopInfo;
import java.util.List;

public interface SellingInfoService {
    List<SellerTopInfo> getTop20SellingUsers();
}