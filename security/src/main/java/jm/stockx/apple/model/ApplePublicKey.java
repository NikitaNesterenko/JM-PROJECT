package jm.stockx.apple.model;

import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * An object that defines a single JSON Web Key.
 */

@Getter
@ToString
public final class ApplePublicKey {

    /**
     * The encryption algorithm used to encrypt the token.
     */
    private final String alg;

    /** The exponent value for the RSA public key. */
    private final String e;

    /** A 10-character identifier key, obtained from your developer account. */
    private final String kid;

    /** The key type parameter setting. This must be set to "RSA". */
    private final String kty;

    /** The modulus value for the RSA public key. */
    private final String n;

    /** The intended use for the public key. */
    private final String use;

    public ApplePublicKey(@JsonProperty("alg") String alg,
                          @JsonProperty("e") String e,
                          @JsonProperty("kid") String kid,
                          @JsonProperty("kty") String kty,
                          @JsonProperty("n") String n,
                          @JsonProperty("use") String use) {
        this.alg = alg;
        this.e = e;
        this.kid = kid;
        this.kty = kty;
        this.n = n;
        this.use = use;
    }
}
