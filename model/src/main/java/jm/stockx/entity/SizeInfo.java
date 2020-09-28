package jm.stockx.entity;

import jm.stockx.enums.ShoeSizeStandards;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "shoe_info")
public class SizeInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "last_sale")
    private String lastSale;

    @Column(name = "lowest_ask")
    private String lowestAsk;

    @Column(name = "highest_bid")
    private String highestBid;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "condition")
    private String condition;

    @Column(name = "shoe_size", precision = 10, scale = 2)
    private Double size ;

    @Column(name = "size_standard", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoeSizeStandards shoeSizeStandards;

    @Columns(columns = { @Column(name = "show_info_currency"), @Column(name = "show_info_retail_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money retailPrice;

    public SizeInfo(Item item) {
        this.retailPrice = item.getRetailPrice();
    }
}
