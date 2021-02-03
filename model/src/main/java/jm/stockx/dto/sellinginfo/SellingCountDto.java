package jm.stockx.dto.sellinginfo;

import lombok.*;

import java.time.Month;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellingCountDto {

    private Long itemId;

    private Month month;

    private Long countOfSellings;
}
