package jm.stockx.api.dao;

import jm.stockx.entity.NotificationInfo;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationInfoDAOImpl extends AbstractDAO<NotificationInfo, Long> implements NotificationInfoDAO{
    @Override
    public void updateBiddingNewLowestAsk(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.BiddingNewLowestAsk = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }


    @Override
    public void updateBiddingNewHighestBid(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.BiddingNewHighestBid = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateBidExpiring(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.BidExpiring = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateBidExpired(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.BidExpired = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateAskMatchesExpiredBid(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.AskMatchesExpiredBid = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateBuyerNearbyMatch(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.BuyerNearbyMatch = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateBidAccepted(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.BidAccepted = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateIPOBidAlmostOutOfTheMoney(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.IPOBidAlmostOutOfTheMoney = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateIPOBidOutOfTheMoney(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.IPOBidOutOfTheMoney = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateIPOBidAccepted(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.IPOBidAccepted = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateAskingNewHighestBid(Long userId, int state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.AskingNewHighestBid = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateAskingNewLowestAsk(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.AskingNewLowestAsk = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateAskExpiring(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.AskExpiring = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateItemSold(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.ItemSold = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateFirstSellerHasNotShipped(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.FirstSellerHasNotShipped = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateSecondSellerHasNotShipped(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.SecondSellerHasNotShipped = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateThirdSellerHasNotShipped(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.ThirdSellerHasNotShipped = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateStockXProductFeatures(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.StockXProductFeatures = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateSneakerNews(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.SneakerNews = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
        public void updateHandbagNews(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.HandbagNews = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateWatchNews(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.WatchNews = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateStreetWearNews(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.StreetWearNews = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updatePriceDropEmail(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.PriceDropEmail = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateWelcomeSeries(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.WelcomeSeries = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void updateCollectiblesNews(Long userId, boolean state) {
        entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo.CollectiblesNews = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

}
