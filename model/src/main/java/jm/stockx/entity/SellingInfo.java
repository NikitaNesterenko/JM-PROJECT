package jm.stockx.entity;

import jm.stockx.dto.iteminfo.ItemInfoDto;
import jm.stockx.enums.Status;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;
import org.joda.money.Money;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
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

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(targetEntity = ItemInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemInfo itemInfo;

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

}
