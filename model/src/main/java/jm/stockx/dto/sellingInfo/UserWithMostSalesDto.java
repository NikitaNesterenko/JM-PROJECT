package jm.stockx.dto.sellingInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserWithMostSalesDto {
    private Long id;
    private String username;
    private Long saleCount;
}
