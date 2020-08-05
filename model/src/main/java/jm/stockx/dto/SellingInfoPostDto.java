package jm.stockx.dto;

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
