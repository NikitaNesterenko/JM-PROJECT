package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.dto.buyingInfo.BuyingInfoPostDto;
import jm.stockx.entity.BuyingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    @Override
    public Long create(BuyingInfoPostDto buyingInfoPostDto) {
        BuyingInfo buyingInfo = BuyingInfo.builder()
                .buyingPrice(buyingInfoPostDto.getBuyingPrice())
                .paymentsInfo(buyingInfoPostDto.getPaymentsInfo())
                .buyingTimeStamp(buyingInfoPostDto.getBuyingTimeStamp())
                .status(buyingInfoPostDto.getStatus())
                .boughtItemsInfo(buyingInfoPostDto.getBoughtItems())
                .build();
        buyingInfoDAO.add(buyingInfo);
        return buyingInfo.getId();
    }

    @Override
    public BuyingInfo getBuyingInfoById(Long id) {
        return buyingInfoDAO.getById(id);
    }
}
