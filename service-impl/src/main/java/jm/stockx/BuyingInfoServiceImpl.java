package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.entity.BuyingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyingInfoServiceImpl implements BuyingInfoService {

    private final BuyingInfoDAO buyingInfoDAO;

    @Autowired
    public BuyingInfoServiceImpl(BuyingInfoDAO buyingInfoDAO) {
        this.buyingInfoDAO = buyingInfoDAO;
    }

    @Override
    public void create(BuyingInfo buyingInfo) {
        buyingInfoDAO.add(buyingInfo);
    }
}
