package jm.stockx.entity;

import jm.stockx.enums.ShoeSizeTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shoe_size")
public class ShoeSize {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", unique = true, precision = 10, scale = 2)
    private Double size;

    @Column(name = "size_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoeSizeTypes sizeTypes;

    @ManyToMany(mappedBy = "sizes", fetch = FetchType.LAZY)
    private Set<ItemInfo> items = new HashSet<>();

    public ShoeSize(Double size, ShoeSizeTypes sizeTypes) {
        this.size = size;
        this.sizeTypes = sizeTypes;
    }

    @Override
    public String toString() {
        return size + " " + sizeTypes;
    }
}
