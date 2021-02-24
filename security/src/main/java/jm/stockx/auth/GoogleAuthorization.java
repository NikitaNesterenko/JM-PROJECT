package jm.stockx.auth;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;
import jm.stockx.entity.User;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class GoogleAuthorization {

    public static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";
    static final String CLIENT_ID = "347711318147-es57tksl762b63c5dhk7oq47kqmfrvph.apps.googleusercontent.com";
    static final String CLIENT_SECRET = "PwPnyCvAefTCs-kA2DwRt1PR";
    static final String CUSTOM_SCOPE = "profile";

    @Getter
    final OAuth20Service service = new ServiceBuilder(CLIENT_ID)
            .apiSecret(CLIENT_SECRET)
            .defaultScope(CUSTOM_SCOPE)
            .responseType("token")
            .callback("http://localhost:8080/authorization/returnCodeGoogle")
            .build(GoogleApi20.instance());

    @Getter
    final String authorizationUrl = service.createAuthorizationUrlBuilder()
            .scope(CUSTOM_SCOPE)
            .build();

    public OAuth2AccessToken getGoogleOAuth2AccessToken (String code) throws InterruptedException, ExecutionException, IOException {
        return service.getAccessToken(AccessTokenRequestParams.create(code).scope(CUSTOM_SCOPE));
    }

    public User getGoogleUser(OAuth2AccessToken token) throws InterruptedException, ExecutionException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(token, request);
        User user = null;

        try (Response response = service.execute(request)) {
            JSONObject jsonObj = new JSONObject(response.getBody());
            JSONArray jArray = jsonObj.getJSONArray("response");
            String password = jArray.getJSONObject(0).optString("sub");
            String firstName = jArray.getJSONObject(0).optString("given_name");
            String lastName = jArray.getJSONObject(0).optString("family_name");
            user = new User(firstName, lastName, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
}
