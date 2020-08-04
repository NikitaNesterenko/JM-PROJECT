package jm.stockx.entity;

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

    @Column(name = "price")
    private Double price;

    @Column(name = "lowest_ask")
    private Double lowestAsk;

    @Column(name = "highest_bid")
    private Double highestBid;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    //new or old
    @Column(name = "item_condition")
    private String condition;

    @ManyToOne(targetEntity = Brand.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "item_image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Byte[] itemImage;

    public Item(String name, Double price, Double lowestAsk, Double highestBid, LocalDate releaseDate,
                String condition) {
        this.name = name;
        this.price = price;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.releaseDate = releaseDate;
        this.condition = condition;
    }

    @OneToOne(targetEntity = Style.class)
    @JoinColumn(name = "style_id")
    private Style style;
}
