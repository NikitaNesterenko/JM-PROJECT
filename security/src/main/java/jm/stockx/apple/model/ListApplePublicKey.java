package jm.stockx.apple.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * A list of JSON Web Key objects.
 */
public final class ListApplePublicKey {

    /**
     * A list that contains JSON Web Key objects.
     */
    private final List<ApplePublicKey> keys;

    public ListApplePublicKey(@JsonProperty("keys") List<ApplePublicKey> keys) {
        this.keys = keys;
    }

    public List<ApplePublicKey> getKeys() {
        return keys;
    }
}
