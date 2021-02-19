package jm.stockx.api.dao;

import jm.stockx.dto.bid.BidDto;
import jm.stockx.entity.Bid;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BidDaoImpl extends AbstractDAO<Bid, Long> implements BidDAO {

    @Override
    public BidDto getBidDtoByBidId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.bid.BidDto(" +
                "b.id," +
                "b.price," +
                "b.success," +
                "b.item.name," +
                "b.user.username)" +
                "FROM Bid AS b " +
                "WHERE b.id =: id", BidDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public BidDto getBidDtoByItemNameAndUserName(String itemName, String userName) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.bid.BidDto(" +
                "b.id," +
                "b.price," +
                "b.success," +
                "b.item.name," +
                "b.user.username)" +
                "FROM Bid AS b, User AS u, Item AS i " +
                "WHERE u.username =: userName AND i.name =: itemName ", BidDto.class)
                .setParameter("userName", userName)
                .setParameter("itemName", itemName)
                .getSingleResult();
    }

    @Override
    public void updateBidPrice(Money price, Long bidId) {
        entityManager.createQuery("" +
                "UPDATE Bid bid SET " +
                "bid.price = :price " +
                "WHERE bid.id = :bidId")
                .setParameter("price", price)
                .setParameter("bidId", bidId)
                .executeUpdate();
    }

    @Override
    public List<BidDto> getHighestBids() {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.bid.BidDto(" +
                "b.id," +
                "b.price," +
                "b.success," +
                "b.itemInfo.item.name," +
                "b.user.username) " +
                "FROM Bid AS b " +
                "ORDER BY b.price DESC", BidDto.class)
                .setMaxResults(5)
                .getResultList();
    }
}