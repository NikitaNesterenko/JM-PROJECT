package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "NotificationInfo")
public class NotificationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "success")
    private String success;

    @Column(name = "bidding_new_lowestAsk")
    private boolean BiddingNewLowestAsk;

    @Column(name = "bidding_new_highestBid")
    private boolean BiddingNewHighestBid;

    @Column(name = "bid_expiring")
    private boolean BidExpiring;

    @Column(name = "bid_expired")
    private boolean BidExpired;

    @Column(name = "ask_matches_expired_bid")
    private boolean AskMatchesExpiredBid;

    @Column(name = "buyer_nearby_match")
    private boolean BuyerNearbyMatch;

    @Column(name = "bid_accepted")
    private boolean BidAccepted;

    @Column(name = "ipo_bid_almost_out_of_the_money")
    private boolean IPOBidAlmostOutOfTheMoney;

    @Column(name = "ipo_bid_out_of_the_money")
    private boolean IPOBidOutOfTheMoney;

    @Column(name = "ipo_bid_Accepted")
    private boolean IPOBidAccepted;

    @Column(name = "asking_new_highest_bid")
    private boolean AskingNewHighestBid;

    @Column(name = "asking_new_lowest_ask")
    private boolean AskingNewLowestAsk;

    @Column(name = "ask_expiring")
    private boolean AskExpiring;

    @Column(name = "ask_expired")
    private boolean AskExpired;

    @Column(name = "item_sold")
    private boolean ItemSold;

    @Column(name = "first_seller_has_not_shipped")
    private boolean FirstSellerHasNotShipped;

    @Column(name = "second_seller_has_not_shipped")
    private boolean SecondSellerHasNotShipped;

    @Column(name = "third_seller_has_not_shipped")
    private boolean ThirdSellerHasNotShipped;

    @Column(name = "stockx_product_features")
    private boolean StockXProductFeatures;

    @Column(name = "sneaker_news")
    private boolean SneakerNews;

    @Column(name = "handbag_news")
    private boolean HandbagNews;

    @Column(name = "watch_news")
    private boolean WatchNews;

    @Column(name = "streetwear_news")
    private boolean StreetwearNews;

    @Column(name = "price_drop_email")
    private boolean PriceDropEmail;

    @Column(name = "welcome_series")
    private boolean WelcomeSeries;

    @Column(name = "collectibles_news")
    private boolean CollectiblesNews;


}
