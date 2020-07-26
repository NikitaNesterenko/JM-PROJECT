package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellerTopInfo {
    private String userName;
    private Integer countOfSell;

    public SellerTopInfo(String userName){
        this.userName = userName;
    }
}
