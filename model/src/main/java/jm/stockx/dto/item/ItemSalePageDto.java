package jm.stockx.dto.item;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemSalePageDto {

    private String               brandName;
    private String               modelName;
    private String               condition;
    private String               ticker;
    private String               authentic;
    private List<SizesAndPrices> sizesAndPricesList;
    private String               imageUrl;

}
