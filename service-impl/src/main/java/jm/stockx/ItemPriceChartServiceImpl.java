package jm.stockx;

import jm.stockx.api.dao.ItemPriceChartDAO;
import jm.stockx.dto.ItemPriceChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPriceChartServiceImpl implements ItemPriceChartService {

    private final ItemPriceChartDAO itemPriceChartDAO;

    @Autowired
    public ItemPriceChartServiceImpl(ItemPriceChartDAO itemPriceChartDAO) {

        this.itemPriceChartDAO = itemPriceChartDAO;

    }

    @Override
    public ItemPriceChartDto get12LatestSales(Long id) {

        return itemPriceChartDAO.get12LatestSales(id);

    }
}
