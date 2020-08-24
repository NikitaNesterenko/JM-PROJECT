package jm.stockx.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Класс расширяющий возможности простого итема, включены дополнительные поля. Класс не стал экстендить,
 * а просто включил в качестве поля класса. Возможно есть вариант лучшей реализации, но я его не сомг придумать.
 * 1. цена за товар хранится в Double. Изменить, как реализуется другой функционал.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="portfolio_item")
public class ItemForPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    private Item item;

    public ItemForPortfolio(LocalDate date, Double purchasePrice, Item item) {
        purchaseDate = date;
        this.purchasePrice = purchasePrice;
        this.item = item;
    }
}
