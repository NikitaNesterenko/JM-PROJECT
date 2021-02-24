package jm.stockx.dto.sellinginfo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellingInfoPostDto {

    private LocalDateTime orderDate;
    private Double price;
}
