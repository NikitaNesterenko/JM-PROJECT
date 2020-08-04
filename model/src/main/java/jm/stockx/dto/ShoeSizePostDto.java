package jm.stockx.dto;

import jm.stockx.entity.ShoeSize;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoeSizePostDto {

    @Positive
    @NotNull
    private Double size;

    public ShoeSizePostDto(ShoeSize shoeSize) {
        this.size = shoeSize.getSize();
    }
}
