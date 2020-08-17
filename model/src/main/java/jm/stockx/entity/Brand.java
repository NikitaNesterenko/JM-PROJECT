package jm.stockx.entity;

import jm.stockx.dto.BrandPostDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public Brand(BrandPostDto brandPostDto) {
        this.name = brandPostDto.getName();
    }
}
