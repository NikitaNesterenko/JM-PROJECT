package jm.stockx;

import jm.stockx.api.dao.SellingInfoDAO;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.dto.sellingInfo.SellerTopInfoDto;
import jm.stockx.entity.SellingInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SellingInfoServiceImpl implements SellingInfoService {
    private final SellingInfoDAO sellingInfoDAO;

    public SellingInfoServiceImpl(SellingInfoDAO sellingInfoDAO) {
        this.sellingInfoDAO = sellingInfoDAO;
    }

    @Override
    public Double getAverageSalesValue() {
        return sellingInfoDAO.getAverageSalesValue();
    }

    @Override
    public List<SellingInfo> getAll() {
        return sellingInfoDAO.getAll();
    }

    @Override
    public SellingInfo get(Long id) {
        return sellingInfoDAO.getById(id);
    }

    @Override
    public void create(SellingInfo sellingInfo) {
        sellingInfoDAO.add(sellingInfo);
    }

    @Override
    public void update(SellingInfo sellingInfo) {
        sellingInfoDAO.update(sellingInfo);
    }

    @Override
    public void delete(Long id) {
        sellingInfoDAO.deleteById(id);
    }

    @Override
    public List<SellerTopInfoDto> getSellerTopInfoDto(){
        return sellingInfoDAO.getSellerTopInfoDto();
    }

    @Override
    public int getCountSalesForPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
        return sellingInfoDAO.getCountSalesForPeriod(beginningPeriod, endPeriod);
    }

    @Override
    public List<ItemTopInfoDto> getItemTopInfoDto(int maxResult) {
        return sellingInfoDAO.getItemTopInfoDto(maxResult);
    }
}
