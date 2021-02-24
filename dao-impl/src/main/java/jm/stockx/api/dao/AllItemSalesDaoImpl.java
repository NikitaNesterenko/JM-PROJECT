package jm.stockx.api.dao;

import jm.stockx.dto.allitemsales.AllItemSalesDto;
import jm.stockx.dto.item.ItemDto;
import jm.stockx.entity.BuyingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AllItemSalesDaoImpl extends AbstractDAO<BuyingInfo, Long> implements AllItemSalesDAO {

    private final ItemDAO itemDAO;

    @Autowired
    public AllItemSalesDaoImpl(ItemDAO itemDAO, EntityManager entityManager) {

        this.itemDAO = itemDAO;

        this.entityManager = entityManager;
    }

    public List<AllItemSalesDto> getAllItemSalesByItem(ItemDto item) {

        return entityManager.createQuery(
                "SELECT NEW jm.stockx.dto.allitemsales.AllItemSalesDto" +
                        "(" +
                        "buyInf.buyingPrice, buyInf.buyingTimeStamp, itemSize" +
                        ") " +
                        "FROM BuyingInfo buyInf " +
                        "JOIN ItemInfo itemInf ON buyInf = itemInf.buyingInfo " +
                        "JOIN ItemSize itemSize ON itemSize = itemInf.size " +
                        "WHERE itemInf.item = :item")
                .setParameter("item", item)
                .getResultList();

    }

    public List<AllItemSalesDto> getAllItemSalesById(Long itemId) {

        ItemDto item = itemDAO.getItemDtoByItemId(itemId);
        return getAllItemSalesByItem(item);

    }

}
