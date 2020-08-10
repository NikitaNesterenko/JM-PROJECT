package jm.stockx;

import jm.stockx.enums.Status;

public interface SellingService {
    Double getAverageSalesValue();
    void updateSellingStatus(Long id, Status status);
}
