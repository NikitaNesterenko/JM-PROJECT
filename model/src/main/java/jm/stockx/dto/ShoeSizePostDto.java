package jm.stockx.dto;

import jm.stockx.entity.ShoeSize;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoeSizePostDto {

    private Double size;

    public ShoeSizePostDto(ShoeSize shoeSize) {
        this.size = shoeSize.getSize();
    }

}
