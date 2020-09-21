package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyingServiceImpl implements BuyingService {
    private final BuyingInfoDAO buyingInfoDAO;

    public BuyingServiceImpl(BuyingInfoDAO buyingInfoDAO) {
        this.buyingInfoDAO = buyingInfoDAO;
    }

    @Transactional
    @Override
    public void updateBuyingStatus(Long id, Status status) {
        BuyingInfo buyingInfo = buyingInfoDAO.getById(id);
        buyingInfo.setStatus(status);
        buyingInfoDAO.update(buyingInfo);
    }

    @Override
    public boolean isBuyingInfoExist(Long id) {
        return buyingInfoDAO.doesItExistEntity(id);
    }


}
