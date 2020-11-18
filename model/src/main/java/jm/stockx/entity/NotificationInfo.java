package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "NotificationInfo")
public class NotificationInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @Column(name = "bidding_new_lowestAsk")
    private boolean BiddingNewLowestAsk = true;

    @Column(name = "bidding_new_highestBid")
    private boolean BiddingNewHighestBid = true;

    @Column(name = "bid_expiring")
    private boolean BidExpiring = true;

    @Column(name = "bid_expired")
    private boolean BidExpired = false;

    @Column(name = "ask_matches_expired_bid")
    private boolean AskMatchesExpiredBid = true;

    @Column(name = "buyer_nearby_match")
    private boolean BuyerNearbyMatch = true;

    @Column(name = "bid_accepted")
    private boolean BidAccepted = false;

    @Column(name = "ipo_bid_almost_out_of_the_money")
    private boolean IPOBidAlmostOutOfTheMoney = true;

    @Column(name = "ipo_bid_out_of_the_money")
    private boolean IPOBidOutOfTheMoney = true;

    @Column(name = "ipo_bid_Accepted")
    private boolean IPOBidAccepted = false;

    @Column(name = "asking_new_highest_bid")
    private int AskingNewHighestBid = 65;

    @Column(name = "asking_new_lowest_ask")
    private boolean AskingNewLowestAsk = true;

    @Column(name = "ask_expiring")
    private boolean AskExpiring = true;

    @Column(name = "ask_expired")
    private boolean AskExpired = true;

    @Column(name = "item_sold")
    private boolean ItemSold = false;

    @Column(name = "first_seller_has_not_shipped")
    private boolean FirstSellerHasNotShipped = false;

    @Column(name = "second_seller_has_not_shipped")
    private boolean SecondSellerHasNotShipped = false;

    @Column(name = "third_seller_has_not_shipped")
    private boolean ThirdSellerHasNotShipped = false;

    @Column(name = "stockx_product_features")
    private boolean StockXProductFeatures = true;

    @Column(name = "sneaker_news")
    private boolean SneakerNews = true;

    @Column(name = "handbag_news")
    private boolean HandbagNews = false;

    @Column(name = "watch_news")
    private boolean WatchNews = false;

    @Column(name = "streetwear_news")
    private boolean StreetWearNews = false;

    @Column(name = "price_drop_email")
    private boolean PriceDropEmail = true;

    @Column(name = "welcome_series")
    private boolean WelcomeSeries = true;

    @Column(name = "collectibles_news")
    private boolean CollectiblesNews = false;

    public NotificationInfo() {
    }
}
