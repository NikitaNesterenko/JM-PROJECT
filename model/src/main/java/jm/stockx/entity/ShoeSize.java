package jm.stockx.entity;

import jm.stockx.enums.ShoeSizeTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shoe_size")
public class ShoeSize {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", unique = true, nullable = false)
    private Double size;

    @Column(name = "size_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShoeSizeTypes sizeTypes;
}
