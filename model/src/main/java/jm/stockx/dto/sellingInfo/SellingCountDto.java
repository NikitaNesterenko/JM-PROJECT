package jm.stockx.dto.sellingInfo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellingCountDto {

    private Long itemId;

    private LocalDate month;

    private Long countOfSellings;
}
