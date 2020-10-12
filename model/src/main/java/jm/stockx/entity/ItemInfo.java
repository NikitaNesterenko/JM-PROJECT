package jm.stockx.entity;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class ItemInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name = "itemInfo_shoe_size", joinColumns = @JoinColumn(name = "shoe_size_id"),
            inverseJoinColumns = @JoinColumn(name = "itemInfo_id"))
    private List<ShoeSize>sizes = new ArrayList<>();

    @Columns(columns = { @Column(name = "item_currency"), @Column(name = "item_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money price;

    @Columns(columns = { @Column(name = "lowest_ask_currency"), @Column(name = "item_lowest_ask") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money lowestAsk;

    @Columns(columns = { @Column(name = "highest_bid_currency"), @Column(name = "item_highest_bid") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money highestBid;

    @OneToOne(targetEntity = Item.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public ItemInfo(List<ShoeSize> sizes, Money price, Money lowestAsk, Money highestBid, Item item) {
        this.sizes = sizes;
        this.price = price;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.item = item;
    }
}
