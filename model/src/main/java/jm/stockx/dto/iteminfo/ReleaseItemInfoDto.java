package jm.stockx.dto.iteminfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseItemInfoDto {
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
