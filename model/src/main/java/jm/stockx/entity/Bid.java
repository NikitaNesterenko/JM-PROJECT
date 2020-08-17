package jm.stockx.entity;

import lombok.*;
import org.joda.money.Money;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Money price;

    @Column(name = "success")
    private Boolean success;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Bid(Money price, Boolean success, User user, Item item) {
        this.price = price;
        this.success = success;
        this.user = user;
        this.item = item;
    }
}
