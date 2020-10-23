package jm.stockx.entity;

import jm.stockx.enums.ItemSizeTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_size")
public class ItemSize {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", unique = true, precision = 10, scale = 2)
    private String size;

    @Column(name = "size_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemSizeTypes sizeTypes;

    public ItemSize(String size, ItemSizeTypes sizeTypes) {
        this.size = size;
        this.sizeTypes = sizeTypes;
    }

    @Override
    public String toString() {
        return size + " " + sizeTypes;
    }
}
