package jm.stockx.entity;

import jm.stockx.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "selling_info")
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

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

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
