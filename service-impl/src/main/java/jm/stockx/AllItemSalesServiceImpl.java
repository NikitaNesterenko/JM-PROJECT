package jm.stockx;

import java.util.List;
import jm.stockx.api.dao.AllItemSalesDAO;
import jm.stockx.dto.allItemSales.AllItemSalesDto;
import jm.stockx.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllItemSalesServiceImpl implements AllItemSalesService {

    private final AllItemSalesDAO DAO;

    @Autowired
    public AllItemSalesServiceImpl(AllItemSalesDAO dao) {

        this.DAO = dao;

    }

    public List<AllItemSalesDto> getAllItemSalesByItem(Item item) {

        return this.DAO.getAllItemSalesByItem(item);

    }

    public List<AllItemSalesDto> getAllItemSalesById(Long id) {

        return this.DAO.getAllItemSalesById(id);

    }

}
