package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyServiceImpl implements BuyService {
    private final BuyingInfoDAO buyingInfoDAO;

    public BuyServiceImpl(BuyingInfoDAO buyingInfoDAO) {
        this.buyingInfoDAO = buyingInfoDAO;
    }

    @Transactional
    @Override
    public void updateBuyingStatus(Long id, Status status) {
        BuyingInfo buyingInfo = buyingInfoDAO.getById(id);
        buyingInfo.setStatus(status);
        buyingInfoDAO.update(buyingInfo);
    }
}
