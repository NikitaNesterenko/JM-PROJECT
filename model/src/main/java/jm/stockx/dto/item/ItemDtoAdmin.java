package jm.stockx.dto.item;

import lombok.*;
import org.joda.money.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDtoAdmin {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate dateRelease;

    @NotNull
    private Money price;

    private String imageUrl;
    
}
