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
public class ShoeSizePutDto {

    @NotNull
    private Long id;

    @Positive
    @NotNull
    private Double size;

    public ShoeSizePutDto(ShoeSize shoeSize) {
        this.id = shoeSize.getId();
        this.size = shoeSize.getSize();
    }

}
