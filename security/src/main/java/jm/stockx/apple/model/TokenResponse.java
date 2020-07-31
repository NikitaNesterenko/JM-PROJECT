package jm.stockx.apple.model;

import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The response token object returned on a successful request.
 */

@Getter
@ToString
public final class TokenResponse {

    /**
     * A JSON Web Token that contains the user’s identity information.
     */
    private final String idToken;

    /**
     * (Reserved for future use) A token used to access allowed data. Currently, no data set has been defined for access.
     */
    private final String accessToken;

    /**
     * The type of access token. It will always be bearer.
     */
    private final String tokenType;

    /**
     * The amount of time, in seconds, before the access token expires.
     */
    private final Long expiresIn;

    /**
     * The refresh token used to regenerate new access tokens. Store this token securely on your server.
     */
    private final String refreshToken;

    public TokenResponse(@JsonProperty("id_token") String idToken,
                         @JsonProperty("access_token") String accessToken,
                         @JsonProperty("token_type") String tokenType,
                         @JsonProperty("expires_in") Long expiresIn,
                         @JsonProperty("refresh_token") String refreshToken) {
        this.idToken = idToken;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }
}
