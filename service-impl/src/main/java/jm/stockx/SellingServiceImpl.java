package jm.stockx;

import jm.stockx.api.dao.SellingInfoDAO;

public class SellingServiceImpl implements SellingService {
    private final SellingInfoDAO sellingInfoDAO;

    public SellingServiceImpl(SellingInfoDAO sellingInfoDAO) {
        this.sellingInfoDAO = sellingInfoDAO;
    }

    @Override
    public Double getAverageSalesValue() {
        return sellingInfoDAO.getAverageSalesValue();
    }
}
