package jm.stockx.apple.model;

import lombok.Getter;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The error object returned after an unsuccessful request.
 */

@Getter
public final class ErrorResponse {

    /**
     * A string that describes the reason for the unsuccessful request. The string consists of a single allowed value.
     * Possible values: invalid_request, invalid_client, invalid_grant, unauthorized_client, unsupported_grant_type,
     * invalid_scope
     */
    private final String error;

    public ErrorResponse(@JsonProperty("error") String error) {
        this.error = error;
    }
}
