package jm.stockx.dto;

import jm.stockx.entity.ShoeSize;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoeSizePutDto {

    private Long id;
    private Double size;

    public ShoeSizePutDto(ShoeSize shoeSize) {
        this.id = shoeSize.getId();
        this.size = shoeSize.getSize();
    }

}
