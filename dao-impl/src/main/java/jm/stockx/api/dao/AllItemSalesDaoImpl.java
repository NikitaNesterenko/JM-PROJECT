package jm.stockx.api.dao;

import jm.stockx.dto.allItemSales.AllItemSalesDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllItemSalesDaoImpl extends AbstractDAO<BuyingInfo, Long> implements AllItemSalesDAO {

    private final ItemDAO itemDAO;

    @Autowired
    public AllItemSalesDaoImpl(ItemDAO itemDAO) {

        this.itemDAO = itemDAO;

    }

    public List<AllItemSalesDto> getAllItemSalesByItem(Item item) {

        return this.entityManager.createQuery("SELECT NEW jm.stockx.dto.allItemSales.AllItemSalesDto(buyInf, itemSize) " +
                "FROM BuyingInfo buyInf " +
                "JOIN ItemInfo itemInf ON buyInf = itemInf.buyingInfo " +
                "JOIN ItemSize itemSize ON itemSize = itemInf.size " +
                "WHERE itemInf.item = :item")
                .setParameter("item", item)
                .getResultList();

    }

    public List<AllItemSalesDto> getAllItemSalesById(Long itemId) {

        Item item = this.itemDAO.getItemById(itemId);
        return this.getAllItemSalesByItem(item);

    }

}
