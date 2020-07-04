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
public class PurchaseInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_time_stamp")
    private LocalDateTime purchaseTimeStamp;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    public PurchaseInfo(Item item) {
        this.purchaseTimeStamp = LocalDateTime.now();
        this.itemId = item.getId();
        this.purchasePrice = item.getPrice();
    }

}
