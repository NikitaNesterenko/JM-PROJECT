package jm.stockx.dto.bid;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
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