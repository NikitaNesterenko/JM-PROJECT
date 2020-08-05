package jm.stockx.dto;

import lombok.*;

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
