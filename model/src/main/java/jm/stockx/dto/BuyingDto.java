package jm.stockx.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingDto {

    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private Long buyerId;

    @NotNull
    private Long sellerId;
}
