package jm.stockx.dto.recommendedItem;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommendedItemDto {

    Long   itemId;
    String brandName;
    String model;
    String imageUrl;

}
