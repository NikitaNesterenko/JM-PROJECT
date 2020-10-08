package jm.stockx.dto.sellingInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

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
