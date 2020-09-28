package jm.stockx.dto;

import jm.stockx.entity.SizeInfo;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SizeInfoDto {

    @NotBlank
    private String lastSale;

    private String lowestAsk;

    @NotBlank
    private String highestBid;

    @NotBlank
    private String itemName;

    private String condition;

}
