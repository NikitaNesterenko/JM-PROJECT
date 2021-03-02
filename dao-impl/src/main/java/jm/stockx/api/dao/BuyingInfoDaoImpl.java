package jm.stockx.api.dao;

import jm.stockx.dto.buyinginfo.BuyingInfoDto;
import jm.stockx.dto.buyinginfo.BuyingInfoPostDto;
import jm.stockx.dto.iteminfo.ItemInfoDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.Status;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public class BuyingInfoDaoImpl extends AbstractDAO<BuyingInfo, Long> implements BuyingInfoDAO {

    @Override
    public Long addBuyingInfo(BuyingInfoPostDto buyingInfoPostDto) {
        Long buyingInfoId = (Long) entityManager.createNativeQuery("" +
                "INSERT INTO buying_info (buying_info_price, buying_info_currency, " +
                "buying_time_stamp, order_status) " +
                "VALUES (:1, :2, :3, :4) RETURNING id;")
                .setParameter("1", buyingInfoPostDto.getBuyingPrice().getAmount().toString())
                .setParameter("2", buyingInfoPostDto.getBuyingPrice().getCurrencyUnit().toString())
                .setParameter("3", buyingInfoPostDto.getBuyingTimeStamp().toString())
                .setParameter("4", buyingInfoPostDto.getStatus().toString())
                .getSingleResult();
        Long paymentInfoId = addPaymentInfo();
        addItemBuyingByPostDto(buyingInfoPostDto, buyingInfoId);
        addBuyingPayment(buyingInfoId, paymentInfoId);
        return buyingInfoId;
    }

//    заглушка
    private Long addPaymentInfo() {
        return (Long) entityManager.createNativeQuery(
                "INSERT INTO payment_info (address, card_expires_date, " +
                        "card_number, city, country, cvv, first_name, last_name, " +
                        "phone_number, state, zip_code) " +
                        "VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11) " +
                        "RETURNING id;")
                .setParameter("1", "address")
                .setParameter("2", "card_expires_date")
                .setParameter("3", "card_number")
                .setParameter("4", "city")
                .setParameter("5", "country")
                .setParameter("6", "cvv")
                .setParameter("7", "first_name")
                .setParameter("8", "last_name")
                .setParameter("9", "phone_number")
                .setParameter("10", "state")
                .setParameter("11", "zip_code")
                .getSingleResult();
    }

    private void addItemBuyingByPostDto(BuyingInfoPostDto buyingInfoPostDto, Long buyingId){
        for (ItemInfo itemInfo : buyingInfoPostDto.getBoughtItems()) {
            addItemBuying(itemInfo.getId(), buyingId);
        }
    }

    @Override
    public Long addBuyingInfo(ItemInfoDto itemInfo) {
        Long id = (Long) entityManager.createNativeQuery("" +
                "INSERT INTO buying_info " +
                "(buying_info_currency, buying_info_price, buying_time_stamp, order_status)" +
                "VALUES (:1, :2, :3, :4)")
                .setParameter("1", itemInfo.getPrice().getCurrencyUnit().toString())
                .setParameter("2", itemInfo.getPrice().getAmount().toString())
                .setParameter("3", LocalDateTime.now())
                .setParameter("4", Status.ACCEPTED)
                .getSingleResult();

        addItemBuying(itemInfo.getId(), id);
        Long paymentId = addPaymentInfo();
        addBuyingPayment(paymentId, id);
        return id;
    }

    private void addItemBuying(Long itemId, Long buyingId) {
        entityManager.createNativeQuery("" +
                "INSERT INTO buying_payment (buying_id, payment_id) " +
                "VALUES (:7, :8);")
                .setParameter("7", buyingId)
                .setParameter("8", itemId)
                .executeUpdate();
    }

    private void addBuyingPayment(Long paymentId, Long buyingId) {
        entityManager.createNativeQuery("" +
                "INSERT INTO buying_payment (buying_id, payment_id) " +
                "VALUES (:7, :8);")
                .setParameter("7", buyingId)
                .setParameter("8", paymentId)
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
                "JOIN BuyingInfo b " +
                "ON b.id = :itemInfo", BuyingInfoDto.class)
                .setParameter("itemInfo", itemInfo.getId())
                .getSingleResult();
    }

}
