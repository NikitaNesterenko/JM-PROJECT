package jm;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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

    @Column(name = "date_release")
    private LocalDate dateRelease;

    //new or old
    @Column(name = "item_condition")
    private String condition;

    @Column(name = "item_image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] itemImage;

    @ManyToMany(mappedBy = "purchasedItems")
    private Set<PurchaseInfo> purchases;
}
