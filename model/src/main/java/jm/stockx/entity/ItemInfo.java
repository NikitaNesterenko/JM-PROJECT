package jm.stockx.entity;

import jm.stockx.enums.ItemCategory;
import jm.stockx.enums.ItemColors;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Builder
public class ItemInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Item.class, fetch = FetchType.LAZY, optional = false,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToOne(targetEntity = ItemSize.class, fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinTable(
            name = "info_item_size_item",
            joinColumns = @JoinColumn(name = "info_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private ItemSize size;

    @ManyToOne(targetEntity = BuyingInfo.class, fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinTable(
            name = "item_info_buying_info",
            joinColumns = @JoinColumn(name = "info_id"),
            inverseJoinColumns = @JoinColumn(name = "buying_id"))
    private BuyingInfo buyingInfo;

    @Columns(columns = {@Column(name = "item_currency"), @Column(name = "item_price")})
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money price;

    @Columns(columns = {@Column(name = "lowest_ask_currency"), @Column(name = "item_lowest_ask")})
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money lowestAsk;

    @Columns(columns = {@Column(name = "highest_bid_currency"), @Column(name = "item_highest_bid")})
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money highestBid;


    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "item_condition")
    private String condition;

    @Column(name = "description", length = 1500)
    private String description;

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @Column(name = "item_category")
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable (
            name = "info_item_brand_item",
            joinColumns = @JoinColumn(name = "infoId"),
            inverseJoinColumns = @JoinColumn(name = "brandId"))
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    private Style style;

    @Column(name = "item_colors")
    @Enumerated(EnumType.STRING)
    private ItemColors itemColors;

}
