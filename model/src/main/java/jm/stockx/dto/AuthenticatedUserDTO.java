package jm.stockx.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserDTO {

    private String token;

    private UserLoginDTO userLoginDTO;
}
