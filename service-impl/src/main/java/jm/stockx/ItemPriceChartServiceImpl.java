package jm.stockx;

import jm.stockx.api.dao.ItemPriceChartDAO;
import jm.stockx.dto.ItemPriceChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPriceChartServiceImpl implements ItemPriceChartService {

    private final ItemPriceChartDAO DAO;

    @Autowired
    public ItemPriceChartServiceImpl(ItemPriceChartDAO dao) {
        DAO = dao;
    }

    @Override
    public ItemPriceChartDto get12LatestSales(Long id) {

        return DAO.get12LatestSales(id);

    }
}
