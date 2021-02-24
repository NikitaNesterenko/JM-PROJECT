package jm.stockx.dto.iteminfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotReleaseItemInfoDto {
    @NotNull
    private String itemName;

    @NotNull
    private String itemCondition;

    @NotNull
    private String itemImgUrl;

    @NotNull
    private LocalDate itemReleaseDate;
}
