package jm;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentInfo paymentInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PurchaseInfo(Item item) {
        this.purchaseTimeStamp = LocalDateTime.now();
        this.purchasePrice = item.getPrice();
    }
}
