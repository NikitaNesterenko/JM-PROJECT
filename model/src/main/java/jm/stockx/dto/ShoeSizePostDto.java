package jm.stockx.dto;

import jm.stockx.enums.ShoeSizeTypes;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoeSizePostDto {

    @Min(3)
    @NotNull
    private Double size;

    @NotNull
    private ShoeSizeTypes sizeTypes;

    public ShoeSizePostDto(Long id, Double shoeSize, ShoeSizeTypes sizeTypes) {
        this.size = shoeSize;
        this.sizeTypes = sizeTypes;
    }
}
