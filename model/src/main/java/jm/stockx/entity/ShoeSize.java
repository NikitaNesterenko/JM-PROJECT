package jm.stockx.entity;

import jm.stockx.enums.ShoeSizeTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "shoe_size")
public class ShoeSize {

    public ShoeSize(Double size, ShoeSizeTypes sizeTypes) {
        this.size = size;
        this.sizeTypes = sizeTypes;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", precision = 10, scale = 2)
    private Double size;

    @Column(name = "size_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoeSizeTypes sizeTypes;

    @OneToMany
    private List<Item> items;
}
