package jm.stockx.dto;

import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoeSizeDto {

    private Long id;

    @Min(3)
    @NotNull
    private Double size;

    @NotNull
    private ShoeSizeTypes sizeTypes;

    public ShoeSizeDto(ShoeSize shoesize) {
        this.id = shoesize.getId();
        this.size = shoesize.getSize();
        this.sizeTypes = shoesize.getSizeTypes();
    }
}
