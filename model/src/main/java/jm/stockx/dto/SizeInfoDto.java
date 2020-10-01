package jm.stockx.dto;

import jm.stockx.enums.ShoeSizeStandards;
import lombok.*;
import org.joda.money.Money;

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

    private Double size;

    private ShoeSizeStandards shoeSizeStandards;

    private Money retailPrice;

}
