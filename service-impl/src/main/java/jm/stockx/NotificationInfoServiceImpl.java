package jm.stockx;

import jm.stockx.api.dao.NotificationInfoDAO;
import jm.stockx.entity.NotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationInfoServiceImpl implements NotificationInfoService {

    private final NotificationInfoDAO notificationInfoDAO;

    @Autowired
    public NotificationInfoServiceImpl(NotificationInfoDAO notificationInfoDAO) {
        this.notificationInfoDAO = notificationInfoDAO;
    }


    @Override
    public void updateBiddingNewLowestAsk(Long userId, boolean state) {
        notificationInfoDAO.updateBiddingNewLowestAsk(userId, state);
    }

    @Override
    public void updateBiddingNewHighestBid(Long userId, boolean state) {
        notificationInfoDAO.updateBiddingNewHighestBid(userId, state);
    }

    @Override
    public void updateBidExpiring(Long userId, boolean state) {
        notificationInfoDAO.updateBidExpiring(userId, state);
    }

    @Override
    public void updateBidExpired(Long userId, boolean state) {
        notificationInfoDAO.updateBidExpired(userId, state);
    }

    @Override
    public void updateAskMatchesExpiredBid(Long userId, boolean state) {
        notificationInfoDAO.updateAskMatchesExpiredBid(userId, state);
    }

    @Override
    public void updateBuyerNearbyMatch(Long userId, boolean state) {
        notificationInfoDAO.updateBuyerNearbyMatch(userId, state);
    }

    @Override
    public void updateBidAccepted(Long userId, boolean state) {
        notificationInfoDAO.updateBidAccepted(userId, state);
    }

    @Override
    public void updateIPOBidAlmostOutOfTheMoney(Long userId, boolean state) {
        notificationInfoDAO.updateIPOBidAlmostOutOfTheMoney(userId, state);
    }

    @Override
    public void updateIPOBidOutOfTheMoney(Long userId, boolean state) {
        notificationInfoDAO.updateIPOBidOutOfTheMoney(userId, state);
    }

    @Override
    public void updateIPOBidAccepted(Long userId, boolean state) {
        notificationInfoDAO.updateIPOBidAccepted(userId, state);
    }

    @Override
    public void updateAskingNewHighestBid(Long userId, int state) {
        notificationInfoDAO.updateAskingNewHighestBid(userId, state);
    }

    @Override
    public void updateAskingNewLowestAsk(Long userId, boolean state) {
        notificationInfoDAO.updateAskingNewLowestAsk(userId, state);
    }

    @Override
    public void updateAskExpiring(Long userId, boolean state) {
        notificationInfoDAO.updateAskExpiring(userId, state);
    }

    @Override
    public void updateItemSold(Long userId, boolean state) {
        notificationInfoDAO.updateItemSold(userId, state);
    }

    @Override
    public void updateFirstSellerHasNotShipped(Long userId, boolean state) {
        notificationInfoDAO.updateFirstSellerHasNotShipped(userId, state);
    }

    @Override
    public void updateSecondSellerHasNotShipped(Long userId, boolean state) {
        notificationInfoDAO.updateSecondSellerHasNotShipped(userId, state);
    }

    @Override
    public void updateThirdSellerHasNotShipped(Long userId, boolean state) {
        notificationInfoDAO.updateThirdSellerHasNotShipped(userId, state);
    }

    @Override
    public void updateStockXProductFeatures(Long userId, boolean state) {
        notificationInfoDAO.updateStockXProductFeatures(userId, state);
    }

    @Override
    public void updateSneakerNews(Long userId, boolean state) {
        notificationInfoDAO.updateSneakerNews(userId, state);
    }

    @Override
    public void updateHandbagNews(Long userId, boolean state) {
        notificationInfoDAO.updateHandbagNews(userId, state);
    }

    @Override
    public void updateWatchNews(Long userId, boolean state) {
        notificationInfoDAO.updateWatchNews(userId, state);
    }

    @Override
    public void updateStreetWearNews(Long userId, boolean state) {
        notificationInfoDAO.updateStreetWearNews(userId, state);
    }

    @Override
    public void updatePriceDropEmail(Long userId, boolean state) {
        notificationInfoDAO.updatePriceDropEmail(userId, state);
    }

    @Override
    public void updateWelcomeSeries(Long userId, boolean state) {
        notificationInfoDAO.updateWelcomeSeries(userId, state);
    }

    @Override
    public void updateCollectiblesNews(Long userId, boolean state) {
        notificationInfoDAO.updateCollectiblesNews(userId, state);
    }

    @Override
    public void createNotificationInfo(NotificationInfo notificationInfo) {
        notificationInfoDAO.add(notificationInfo);
    }

    @Override
    public void updateField(Long userId, String nameField, boolean state){
        System.out.println("Update_field -- " + userId + "      " + nameField + "    " + state);
        notificationInfoDAO.updateField(userId, nameField, state);
        System.out.println("AFTER UPDATE");
    }

    @Override
    public NotificationInfo getNotificationInfoById(Long userId) {
        return  notificationInfoDAO.getById(userId);
    }

}
