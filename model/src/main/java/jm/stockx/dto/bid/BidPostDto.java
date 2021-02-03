package jm.stockx.dto.bid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidPostDto {

    private Long id;

    private String price;

    private Boolean success;

    @NotBlank
    private Long itemInfoId;

    @NotBlank
    private Long userId;
}
