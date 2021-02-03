package jm.stockx.auth;

import com.github.scribejava.apis.VkontakteApi;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
public class VkAuthorisation {

    public static final String PROTECTED_RESOURCE_URL = "https://api.vk.com/method/users.get?v=5.92";
    static final String CLIENT_ID = "7535082";
    static final String CLIENT_SECRET = "2DVvAzRziipUlJ9AZXq9";
    static final String CUSTOM_SCOPE = "email";

    @Getter
    final OAuth20Service service = new ServiceBuilder(CLIENT_ID)

            .apiSecret(CLIENT_SECRET)
            .defaultScope(CUSTOM_SCOPE)
            .responseType("token")
            .callback("http://localhost:8080/authorization/returnCodeVK")
            .build(VkontakteApi.instance());

    @Getter
    final String authorizationUrl = service.createAuthorizationUrlBuilder()
            .scope(CUSTOM_SCOPE)
            .build();

    public OAuth2AccessToken toGetTokenVK(String code) throws InterruptedException, ExecutionException, IOException {
        return service.getAccessToken(AccessTokenRequestParams.create(code).scope(CUSTOM_SCOPE));
    }

    public User toCreateUser(OAuth2AccessToken token) throws InterruptedException, ExecutionException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(token, request);
        User user = null;

        try (Response response = service.execute(request)) {
            JSONObject jsonObj = new JSONObject(response.getBody());
            JSONArray jArray = jsonObj.getJSONArray("response");
            String password = jArray.getJSONObject(0).optString("id");
            String firstName = jArray.getJSONObject(0).optString("first_name");
            String lastName = jArray.getJSONObject(0).optString("last_name");
            user = new User(firstName, lastName, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
