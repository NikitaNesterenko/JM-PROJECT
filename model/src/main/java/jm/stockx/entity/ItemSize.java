package jm.stockx.entity;

import jm.stockx.enums.ItemSizeTypes;
import lombok.*;

import javax.persistence.*;

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
