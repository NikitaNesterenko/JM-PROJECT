package jm.stockx.entity;

import jm.stockx.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
public class BuyingInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buying_time_stamp")
    private LocalDateTime buyingTimeStamp;

    @Column(name = "buying_price", precision = 10, scale = 2)
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
