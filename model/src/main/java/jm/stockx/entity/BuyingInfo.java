package jm.stockx.entity;

import jm.stockx.enums.Status;
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
@Table(name = "buying_info")

/* Таблица хранит историю покупок для КАЖДОГО Item-а */
public class BuyingInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buying_time_stamp")
    private LocalDateTime buyingTimeStamp;

    @Column(name = "buying_price")
    private Double buyingPrice;

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
