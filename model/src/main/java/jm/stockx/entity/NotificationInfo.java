package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NotificationInfo")
public class NotificationInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "bidding_new_lowestAsk")
    private boolean biddingNewLowestAsk = true;

    @Column(name = "bidding_new_highestBid")
    private boolean biddingNewHighestBid = true;

    @Column(name = "bid_expiring")
    private boolean bidExpiring = true;

    @Column(name = "bid_expired")
    private boolean bidExpired = false;

    @Column(name = "ask_matches_expired_bid")
    private boolean askMatchesExpiredBid = true;

    @Column(name = "buyer_nearby_match")
    private boolean buyerNearbyMatch = true;

    @Column(name = "bid_accepted")
    private boolean bidAccepted = false;

    @Column(name = "ipo_bid_almost_out_of_the_money")
    private boolean iPOBidAlmostOutOfTheMoney = true;

    @Column(name = "ipo_bid_out_of_the_money")
    private boolean iPOBidOutOfTheMoney = true;

    @Column(name = "ipo_bid_Accepted")
    private boolean iPOBidAccepted = false;

    @Column(name = "asking_new_highest_bid")
    private int askingNewHighestBid = 65;

    @Column(name = "asking_new_lowest_ask")
    private boolean askingNewLowestAsk = true;

    @Column(name = "ask_expiring")
    private boolean askExpiring = true;

    @Column(name = "ask_expired")
    private boolean askExpired = true;

    @Column(name = "item_sold")
    private boolean itemSold = false;

    @Column(name = "first_seller_has_not_shipped")
    private boolean firstSellerHasNotShipped = false;

    @Column(name = "second_seller_has_not_shipped")
    private boolean secondSellerHasNotShipped = false;

    @Column(name = "third_seller_has_not_shipped")
    private boolean thirdSellerHasNotShipped = false;

    @Column(name = "stockx_product_features")
    private boolean stockXProductFeatures = true;

    @Column(name = "sneaker_news")
    private boolean sneakerNews = true;

    @Column(name = "handbag_news")
    private boolean handbagNews = false;

    @Column(name = "watch_news")
    private boolean watchNews = false;

    @Column(name = "streetwear_news")
    private boolean streetWearNews = false;

    @Column(name = "price_drop_email")
    private boolean priceDropEmail = true;

    @Column(name = "welcome_series")
    private boolean welcomeSeries = true;

    @Column(name = "collectibles_news")
    private boolean collectiblesNews = false;

    public void setUser(User user) {
        this.user = user;
    }

}
