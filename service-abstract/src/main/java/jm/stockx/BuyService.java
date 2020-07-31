package jm.stockx;

import jm.stockx.entity.Item;
import jm.stockx.enums.Status;

public interface BuyService {

    void updateBuyingStatus(Long id, Status status);
}
