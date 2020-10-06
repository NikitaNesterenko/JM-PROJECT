package jm.stockx.entity;

import jm.stockx.enums.ItemCategory;
import jm.stockx.enums.ItemColors;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;
import org.joda.money.Money;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.List;

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

    @Columns(columns = {@Column(name = "retail_price_currency"), @Column(name = "item_retail_price")})
    @Type(type = "joda_MoneyAmountWithCurrencyType")
    private Money retailPrice;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    //new or old
    @Column(name = "item_condition")
    private String condition;

    @Column(name = "description", length = 1500)
    private String description;

    // @ManyToOne(targetEntity = Brand.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)     - так валится
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    private Style style;

    @Column(name = "item_colors")
    @Enumerated(EnumType.STRING)
    private ItemColors itemColors;

    @Column(name = "item_category")
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @ManyToMany
    private Set<BuyingInfo> buyingInfoSet;

    public Item(Long id,
                String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description) {
        this.id = id;
        this.name = name;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
    }

    public Item(Long id,
                String name,
                Money retailPrice,
                String condition,
                String description) {
        this.id = id;
        this.name = name;
        this.retailPrice = retailPrice;
        this.condition = condition;
        this.description = description;
    }

    public Item(String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
    }

    public Item(String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand) {
        this(name, retailPrice, releaseDate, condition, description);
        this.brand = brand;
    }

    public Item(String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand,
                Style style) {
        this(name, retailPrice, releaseDate, condition, description, brand);
        this.style = style;
    }

    public Item(String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand,
                Style style,
                ItemCategory itemCategory) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
        this.brand = brand;
        this.style = style;
        this.itemCategory = itemCategory;
    }

    public Item(String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand,
                String itemImageUrl,
                Style style) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
        this.brand = brand;
        this.itemImageUrl = itemImageUrl;
        this.style = style;
    }

    public Item(String name,
                Money retailPrice,
                LocalDate releaseDate,
                String condition,
                String description,
                Brand brand,
                String itemImageUrl,
                Style style,
                Money price,
                Money lowestAsk,
                Money highestBid,
                Set<ShoeSize> sizes) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
        this.brand = brand;
        this.itemImageUrl = itemImageUrl;
        this.style = style;
        ItemInfo itemInfo = new ItemInfo(sizes, price, lowestAsk, highestBid, this);
    }

}
