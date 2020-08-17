package jm.stockx.entity;

import jm.stockx.enums.ItemColors;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

    @Column(name = "retail_price", precision = 10, scale = 2)
    private Double retailPrice;

    @Column(name = "lowest_ask", precision = 10, scale = 2)
    private Double lowestAsk;

    @Column(name = "highest_bid", precision = 10, scale = 2)
    private Double highestBid;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    //new or old
    @Column(name = "item_condition")
    private String condition;

    // @ManyToOne(targetEntity = Brand.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)     - так валится
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "item_imageUrl")
    private String itemImageUrl;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    @Column(name = "item_colors")
    @Enumerated(EnumType.STRING)
    private ItemColors itemColors;

    public Item(Long id, String name, Double price,  Double retailPrice, Double lowestAsk, Double highestBid, LocalDate releaseDate, String condition) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.releaseDate = releaseDate;
        this.condition = condition;
    }

    public Item(Long id, String name, Double price, Double retailPrice, Double lowestAsk, Double highestBid, String condition) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.condition = condition;
    }

    public Item(String name,
                Double price,
                Double retailPrice,
                Double lowestAsk,
                Double highestBid,
                LocalDate releaseDate,
                String condition) {
        this.name = name;
        this.price = price;
        this.retailPrice = retailPrice;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.releaseDate = releaseDate;
        this.condition = condition;
    }

    public Item(String name,
                Double price,
                Double retailPrice,
                Double lowestAsk,
                Double highestBid,
                LocalDate releaseDate,
                String condition,
                Brand brand) {
        this(name, price, retailPrice, lowestAsk, highestBid, releaseDate, condition);
        this.brand = brand;
    }

    public Item(String name,
                Double price,
                Double retailPrice,
                Double lowestAsk,
                Double highestBid,
                LocalDate releaseDate,
                String condition,
                Brand brand,
                Style style) {
        this(name, price, retailPrice, lowestAsk, highestBid, releaseDate, condition, brand);
        this.style = style;
    }

    public Item(String name,
                Double price,
                Double retailPrice,
                Double lowestAsk,
                Double highestBid,
                LocalDate releaseDate,
                String condition,
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
        this.brand = brand;
        this.itemImageUrl = itemImageUrl;
        this.style = style;
    }
}
