package jm.stockx.dto.shoesize;

import jm.stockx.dto.ItemSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;
import lombok.*;

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
    private String size;

    @NotNull
    private ItemSizeTypes sizeTypes;

    public ShoeSizeDto(ItemSize shoesize) {
        this.id = shoesize.getId();
        this.size = shoesize.getSize();
        this.sizeTypes = shoesize.getSizeTypes();
    }

    public ShoeSizeDto(ItemSizeDto shoeSizeDto) {
        this.id = shoeSizeDto.getId();
        this.size = shoeSizeDto.getSize();
        this.sizeTypes = shoeSizeDto.getSizeTypes();
    }
}
