package jm.stockx.dto.security.token;

import jm.stockx.entity.TokenRecovery;
import jm.stockx.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenRecoveryPutDto {

    @NotNull
    private Long id;

    @NotNull
    private User user;

    @NotNull
    private String hash;

    @NotNull
    private String hashEmail;

    @NotNull
    private Date startTime;

    public TokenRecoveryPutDto(TokenRecovery tokenRecovery) {
        this.id = tokenRecovery.getId();
        this.user = tokenRecovery.getUser();
        this.hash = tokenRecovery.getHash();
        this.hashEmail = tokenRecovery.getHashEmail();
        this.startTime = tokenRecovery.getStartTime();
    }

}
