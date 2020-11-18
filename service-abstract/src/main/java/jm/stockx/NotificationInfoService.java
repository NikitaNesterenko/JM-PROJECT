package jm.stockx;

import jm.stockx.entity.NotificationInfo;

public interface NotificationInfoService {
    void updateBiddingNewLowestAsk(Long userId, boolean state);
    void updateBiddingNewHighestBid(Long userId, boolean state);
    void updateBidExpiring(Long userId, boolean state);
    void updateBidExpired(Long userId, boolean state);
    void updateAskMatchesExpiredBid(Long userId, boolean state);
    void updateBuyerNearbyMatch(Long userId, boolean state);
    void updateBidAccepted(Long userId, boolean state);
    void updateIPOBidAlmostOutOfTheMoney(Long userId, boolean state);
    void updateIPOBidOutOfTheMoney(Long userId, boolean state);
    void updateIPOBidAccepted(Long userId, boolean state);
    void updateAskingNewHighestBid(Long userId, int state);
    void updateAskingNewLowestAsk(Long userId, boolean state);
    void updateAskExpiring(Long userId, boolean state);
    void updateItemSold(Long userId, boolean state);
    void updateFirstSellerHasNotShipped(Long userId, boolean state);
    void updateSecondSellerHasNotShipped(Long userId, boolean state);
    void updateThirdSellerHasNotShipped(Long userId, boolean state);
    void updateStockXProductFeatures(Long userId, boolean state);
    void updateSneakerNews(Long userId, boolean state);
    void updateHandbagNews(Long userId, boolean state);
    void updateWatchNews(Long userId, boolean state);
    void updateStreetWearNews(Long userId, boolean state);
    void updatePriceDropEmail(Long userId, boolean state);
    void updateWelcomeSeries(Long userId, boolean state);
    void updateCollectiblesNews(Long userId, boolean state);
    void createNotificationInfo(NotificationInfo notificationInfo);





}
