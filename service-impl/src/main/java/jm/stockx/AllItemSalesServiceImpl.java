package jm.stockx;

import jm.stockx.api.dao.AllItemSalesDAO;
import jm.stockx.dto.allitemsales.AllItemSalesDto;
import jm.stockx.dto.item.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllItemSalesServiceImpl implements AllItemSalesService {

    private final AllItemSalesDAO dao;

    @Autowired
    public AllItemSalesServiceImpl(AllItemSalesDAO dao) {

        this.dao = dao;

    }

    public List<AllItemSalesDto> getAllItemSalesByItem(ItemDto item) {

        return this.dao.getAllItemSalesByItem(item);

    }

    public List<AllItemSalesDto> getAllItemSalesById(Long id) {

        return this.dao.getAllItemSalesById(id);

    }

}
