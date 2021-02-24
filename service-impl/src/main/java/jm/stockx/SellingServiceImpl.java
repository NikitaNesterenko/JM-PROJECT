package jm.stockx;

import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellingServiceImpl implements SellingService {
    private final SellingInfoDAO sellingInfoDAO;

    public SellingServiceImpl(SellingInfoDAO sellingInfoDAO) {
        this.sellingInfoDAO = sellingInfoDAO;
    }

    @Override
    public Double getAverageSalesValue() {
        return sellingInfoDAO.getAverageSalesValue();
    }

    @Transactional
    @Override
    public void updateSellingStatus(Long id, Status status) {
        SellingInfo sellingInfo = sellingInfoDAO.getById(id);
        sellingInfo.setStatus(status);
        sellingInfoDAO.update(sellingInfo);
    }


}
