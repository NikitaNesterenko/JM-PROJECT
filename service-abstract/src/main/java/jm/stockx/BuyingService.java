package jm.stockx;

import jm.stockx.enums.Status;

public interface BuyingService {

    void updateBuyingStatus(Long id, Status status);
}
