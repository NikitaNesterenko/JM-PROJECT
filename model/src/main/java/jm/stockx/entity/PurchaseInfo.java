package jm.stockx.entity;

import lombok.*;

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
@Table(name = "purchase_info")

/* Таблица хранит историю покупок для КАЖДОГО Item-а */
public class PurchaseInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_time_stamp")
    private LocalDateTime purchaseTimeStamp;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "purchase_item",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> purchasedItems;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "purchase_payment",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private Set<PaymentInfo> paymentsInfo;

    /* покупка может быть совершена ТОЛЬКО одним покупателем, но один покупатель может совершить много покупок */
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;

    public PurchaseInfo(Item item) {
        this.purchaseTimeStamp = LocalDateTime.now();
        this.purchasePrice = item.getPrice();
    }
}
