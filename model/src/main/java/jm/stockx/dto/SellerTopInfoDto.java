package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellerTopInfoDto {
    private String userName;
    private Integer countOfSell;

    public SellerTopInfoDto(String userName){
        this.userName = userName;
    }
}