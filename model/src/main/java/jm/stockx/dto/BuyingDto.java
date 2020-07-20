package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingDto {
    private Long itemId;
    private Long buyerId;
    private Long sellerId;
}
