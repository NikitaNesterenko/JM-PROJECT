package jm.stockx.dto;

import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StyleDto {

    @NotNull
    private Long id;
    private String name;

}
