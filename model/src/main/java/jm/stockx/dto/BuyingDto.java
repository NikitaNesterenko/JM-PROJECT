package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuyingDto {

    @Null                                   // автогенерация в БД
    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private Long buyerId;

    @NotNull
    private Long sellerId;
}
