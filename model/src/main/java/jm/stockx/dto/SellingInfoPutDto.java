package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellingInfoPutDto {

    @NonNull
    private Long id;

    @NotNull
    private LocalDateTime orderDate;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double price;
}
