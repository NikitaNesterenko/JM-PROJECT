package jm.stockx.dto.item;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemPurchaseDto {

    private String itemCategory;
    private Integer count;

}
