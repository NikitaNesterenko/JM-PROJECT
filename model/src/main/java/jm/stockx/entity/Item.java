package jm.stockx.entity;

import jm.stockx.enums.ItemColors;
import jm.stockx.enums.ItemDirection;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
                List<ShoeSize> sizes,
                ItemDirection itemDirection) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.releaseDate = releaseDate;
        this.condition = condition;
        this.description = description;
        this.brand = brand;
        this.itemImageUrl = itemImageUrl;
        this.style = style;
        ItemInfo itemInfo = new ItemInfo(sizes, price, lowestAsk, highestBid, this, itemDirection);
    }

}
