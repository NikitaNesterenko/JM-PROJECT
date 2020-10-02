package jm.stockx.entity;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;
import org.joda.money.Money;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bids")
@TypeDef(name = "joda_MoneyAmountWithCurrencyType", typeClass = PersistentMoneyAmountAndCurrency.class)
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Columns(columns = { @Column(name = "bid_currency"), @Column(name = "bid_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money price;

    @Column(name = "success")
    private Boolean success;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Bid(Money price, Boolean success, User user, Item item) {
        this.price = price;
        this.success = success;
        this.user = user;
        this.item = item;
    }
}
