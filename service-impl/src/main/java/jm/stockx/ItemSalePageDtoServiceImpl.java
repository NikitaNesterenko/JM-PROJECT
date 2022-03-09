package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.ItemListSalesDAO;
import jm.stockx.dto.item.ItemSalePageDto;
import jm.stockx.dto.item.SizesAndPrices;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemSalePageDtoServiceImpl implements ItemSalePageDtoService {

    private final ItemInfoDAO      itemInfoDAO;
    private final ItemListSalesDAO itemSaleDAO;

    @Autowired
    public ItemSalePageDtoServiceImpl(ItemInfoDAO itemInfoDAO, ItemListSalesDAO itemSaleDAO) {

        this.itemInfoDAO = itemInfoDAO;
        this.itemSaleDAO = itemSaleDAO;

    }


    @Override
    public ItemSalePageDto getById(Long id) {

        ItemInfo itemInfo = itemInfoDAO.getItemInfoByItemId(id);
        List<SizesAndPrices> sizesAndPricesList = itemSaleDAO.getSizesAndPricesByItemId(id);

        return new ItemSalePageDto(
                itemInfo.getBrand().getName(),
                itemInfo.getItem().getName(),
                itemInfo.getCondition(),
                "ticker",
                "authentic",
                sizesAndPricesList,
                itemInfo.getItemImageUrl()
        );

    }
}
