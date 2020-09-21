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
    private String itemName;

    @NotNull
    private String itemCondition;

    @NotNull
    private String itemImgUrl;

    @NotNull
    private Money itemLowestAsk;

    @NotNull
    private LocalDate itemReleaseDate;
}
