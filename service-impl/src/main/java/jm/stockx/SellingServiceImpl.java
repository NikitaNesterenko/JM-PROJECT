package jm.stockx;

import jm.stockx.api.dao.SellingDAO;

public class SellingServiceImpl implements SellingService {
    private final SellingDAO sellingDAO;

    public SellingServiceImpl(SellingDAO sellingDAO) {
        this.sellingDAO = sellingDAO;
    }

    @Override
    public Double getAverageSalesValue() {
        return sellingDAO.getAverageSalesValue();
    }
}
