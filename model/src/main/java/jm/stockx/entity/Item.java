package jm.stockx.entity;

import jm.stockx.enums.ItemColors;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "items")
@TypeDef(name = "joda_MoneyAmountWithCurrencyType", typeClass = PersistentMoneyAmountAndCurrency.class)
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Columns(columns = { @Column(name = "item_currency"), @Column(name = "item_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money price;

    @Columns(columns = { @Column(name = "retail_price_currency"), @Column(name = "item_retail_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money retailPrice;

    @Columns(columns = { @Column(name = "lowest_ask_currency"), @Column(name = "item_lowest_ask") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money lowestAsk;

    @Columns(columns = { @Column(name = "highest_bid_currency"), @Column(name = "item_highest_bid") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money highestBid;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    //new or old
    @Column(name = "item_condition")
    private String condition;

    @Column(name = "description", length = 1500)
    private String description;

    // @ManyToOne(targetEntity = Brand.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)     - так валится
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    @Column(name = "item_colors")
    @Enumerated(EnumType.STRING)
    private ItemColors itemColors;

    @Columns(columns = { @Column(name = "last_sale_price_currency"), @Column(name = "item_last_sale_price") })
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money lastSalePrice;

    public Item(Long id, String name,
                Money price,  Money retailPrice,
                Money lowestAsk, Money highestBid,
                LocalDate releaseDate, String condition,
                String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
    }

    public Item(Long id, String name,
                Money price, Money retailPrice,
                Money lowestAsk, Money highestBid,
                String condition, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.condition = condition;
        this.description = description;
    }

    public Item(String name,
                Money price,
                Money retailPrice,
                Money lowestAsk,
                Money highestBid,
                LocalDate releaseDate,
                String condition,
                String description) {
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
    }

    public Item(String name,
                Money price,
                Money retailPrice,
                Money lowestAsk,
                Money highestBid,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand) {
        this(name, price, retailPrice, lowestAsk, highestBid, releaseDate, condition, description);
        this.brand = brand;
    }

    public Item(String name,
                Money price,
                Money retailPrice,
                Money lowestAsk,
                Money highestBid,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand,
                Style style) {
        this(name, price, retailPrice, lowestAsk, highestBid, releaseDate, condition, description, brand);
        this.style = style;
    }

    public Item(String name,
                Money price,
                Money retailPrice,
                Money lowestAsk,
                Money highestBid,
                LocalDate releaseDate,
                String condition ,
                String description,
                Brand brand,
                String itemImageUrl,
                Style style) {
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
        this.brand = brand;
        this.itemImageUrl = itemImageUrl;
        this.style = style;
    }
}
