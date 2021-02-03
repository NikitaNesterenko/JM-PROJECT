package jm.stockx.api.dao;

import jm.stockx.dto.buyinginfo.BuyingInfoDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import org.springframework.stereotype.Repository;


@Repository
public class BuyingInfoDaoImpl extends AbstractDAO<BuyingInfo, Long> implements BuyingInfoDAO {

    @Override
    public BuyingInfoDto getBuyingInfoDtoByBuyingInfoId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.buyinginfo.BuyingInfoDto(" +
                "b.id, " +
                "b.buyingTimeStamp, " +
                "b.buyingPrice," +
                "b.boughtItemsInfo," +
                "b.paymentsInfo," +
                "b.status)" +
                "FROM BuyingInfo AS b " +
                "WHERE b.id =: id", BuyingInfoDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public BuyingInfo getBuyingInfoByItemInfo(ItemInfo itemInfo) {
        return entityManager.createQuery("" +
                "SELECT b FROM ItemInfo i " +
                "JOIN i.buyingInfo b", BuyingInfo.class)
                //.setParameter("itemInfo", itemInfo)
                .getSingleResult();
    }
}
