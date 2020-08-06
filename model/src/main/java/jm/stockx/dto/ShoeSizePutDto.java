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
public class ShoeSizePutDto {

    @NotNull
    private Long id;

    @Min(3)
    @NotNull
    private Double size;

    @NotNull
    private ShoeSizeTypes sizeTypes;
}
