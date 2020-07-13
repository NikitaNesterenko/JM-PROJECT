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
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_info_id", referencedColumnName = "id")
    private PaymentInfo paymentInfo;


    public PurchaseInfo(Item item) {
        this.purchaseTimeStamp = LocalDateTime.now();
//        this.itemId = item.getId();
        this.purchasePrice = item.getPrice();
    }
}
