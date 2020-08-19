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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "selling_info")
@TypeDef(name = "joda_MoneyAmountWithCurrencyType", typeClass = PersistentMoneyAmountAndCurrency.class)
public class SellingInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Columns(columns = { @Column(name = "selling_info_currency"), @Column(name = "selling_info_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money price;

    public SellingInfo(User user,
                       Item item,
                       Status status) {
        this.user = user;
        this.item = item;
        this.price = item.getPrice();
        this.orderDate = LocalDateTime.now();
        this.status = status;
    }

    public SellingInfo(User user,
                       Item item) {
        this.user = user;
        this.item = item;
        this.price = item.getPrice();
        this.orderDate = LocalDateTime.now();
    }

    public SellingInfo(User user,
                       Item item,
                       Long orderNumber,
                       Status status) {
        this(user, item, status);
        this.orderNumber = orderNumber;
    }
}
