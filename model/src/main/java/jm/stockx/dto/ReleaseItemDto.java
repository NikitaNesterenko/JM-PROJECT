package jm.stockx.dto;

import lombok.*;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseItemDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String condition;

    @NotNull
    private String itemImageUrl;

    @NotNull
    private Money retailPrice;

    @NotNull
    private LocalDate releaseDate;
}