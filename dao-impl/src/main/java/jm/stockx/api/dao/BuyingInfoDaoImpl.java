package jm.stockx.api.dao;

import jm.stockx.dto.buyinginfo.BuyingInfoDto;
import jm.stockx.dto.buyinginfo.BuyingInfoPostDto;
import jm.stockx.dto.userportfolio.BuyingDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import org.springframework.stereotype.Repository;


@Repository
public class BuyingInfoDaoImpl extends AbstractDAO<BuyingInfo, Long> implements BuyingInfoDAO {

//    TODO
    @Override
    public Long addBuyingInfo(BuyingInfoPostDto buyingInfoPostDto) {
        Long id = (Long) entityManager.createNativeQuery("" +
                "INSERT INTO buying_info (buying_info_price, buying_info_currency, " +
                "buying_time_stamp, order_status) " +
                "VALUES (:1, :2, :3, :4) RETURNING id;")
                .setParameter("1", buyingInfoPostDto.getBuyingPrice().getAmount().toString())
                .setParameter("2", buyingInfoPostDto.getBuyingPrice().getCurrencyUnit().toString())
                .setParameter("3", buyingInfoPostDto.getBuyingTimeStamp().toString())
                .setParameter("4", buyingInfoPostDto.getStatus().toString())
                .getSingleResult();

        while (buyingInfoPostDto.getBoughtItems().iterator().hasNext()) {
            entityManager.createNativeQuery("" +
                    "INSERT INTO item_info_buying_info (buying_id, item_id) " +
                    "VALUES (:5, :6);")
                    .setParameter("5", id)
                    .setParameter("6", buyingInfoPostDto.getBoughtItems().iterator().next().getId())
                    .executeUpdate();
        }

        while (buyingInfoPostDto.getBoughtItems().iterator().hasNext()) {
            entityManager.createNativeQuery("" +
                    "INSERT INTO buying_payment (buying_id, payment_id) " +
                    "VALUES (:7, :8);")
                    .setParameter("7", id)
                    .setParameter("8", buyingInfoPostDto.getPaymentsInfo().iterator().next().getId())
                    .executeUpdate();
        }

        return id;
    }

//    TODO не доделал
    @Override
    public void addBuyingInfo(BuyingDto buyingDto) {
        entityManager.createNativeQuery("" +
                "INSERT INTO buying_info " +
                "(buying_info_currency, buying_info_price, buying_time_stamp, order_status)" +
                "VALUES (:1, :2, :3, :4)")
                .setParameter("1", )
                .setParameter()
                .setParameter()
                .setParameter()
                .executeUpdate();
    }

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

//    TODO
    public BuyingInfoDto getBuyingInfoDtoByItemInfo(ItemInfo itemInfo) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.buyinginfo.BuyingInfoDto(" +
                "b.id, " +
                "b.buyingTimeStamp, " +
                "b.buyingPrice," +
                "b.boughtItemsInfo," +
                "b.paymentsInfo," +
                "b.status)" +
                "FROM ItemInfo i " +
                "JOIN ON i.buyingInfo b = :itemInfo", BuyingInfoDto.class)
                .setParameter("itemInfo", itemInfo.getId())
                .getSingleResult();
    }

}
