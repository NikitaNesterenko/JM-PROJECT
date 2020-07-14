package jm;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentInfo paymentInfo;

    @ManyToMany(mappedBy = "purchases")
    private Set<User> users;

    public PurchaseInfo(Item item) {
        this.purchaseTimeStamp = LocalDateTime.now();
        this.purchasePrice = item.getPrice();
    }
}
