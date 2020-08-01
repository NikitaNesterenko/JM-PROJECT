package jm.stockx;

import jm.stockx.api.dao.SellingDAO;
import jm.stockx.entity.SellingInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellingInfoServiceImpl implements SellingInfoService {
    private final SellingDAO sellingDAO;

    public SellingInfoServiceImpl(SellingDAO sellingDAO) {
        this.sellingDAO = sellingDAO;
    }

    @Override
    public Double getAverageSalesValue() {
        return sellingDAO.getAverageSalesValue();
    }

    @Override
    public List<SellingInfo> getAll() {
        return sellingDAO.getAll();
    }

    @Override
    public SellingInfo get(Long id) {
        return sellingDAO.getById(id);
    }

    @Override
    public void create(SellingInfo sellingInfo) {
        sellingDAO.add(sellingInfo);
    }

    @Override
    public void update(SellingInfo sellingInfo) {
        sellingDAO.update(sellingInfo);
    }

    @Override
    public void delete(Long id) {
        sellingDAO.deleteById(id);
    }
}
