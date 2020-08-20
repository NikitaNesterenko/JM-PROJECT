package jm.stockx.entity;

import jm.stockx.enums.Status;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;
import org.joda.money.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "buying_info")
@TypeDef(name = "joda_MoneyAmountWithCurrencyType", typeClass = PersistentMoneyAmountAndCurrency.class)
public class BuyingInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buying_time_stamp")
    private LocalDateTime buyingTimeStamp;

    @Columns(columns = { @Column(name = "buying_info_currency"), @Column(name = "buying_info_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money buyingPrice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "buying_item",
            joinColumns = @JoinColumn(name = "buying_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> boughtItems;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "buying_payment",
            joinColumns = @JoinColumn(name = "buying_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private Set<PaymentInfo> paymentsInfo;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public BuyingInfo(Item item) {
        this.buyingTimeStamp = LocalDateTime.now();
        this.buyingPrice = item.getPrice();
    }
}
