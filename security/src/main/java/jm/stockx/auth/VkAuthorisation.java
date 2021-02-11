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
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
public class VkAuthorisation {

    public final String protectedResourceUrl = "https://api.vk.com/method/users.get?v=5.92";
    final String clientId = "7535082";
    final String clientSecret = "2DVvAzRziipUlJ9AZXq9";
    final String customScope = "email";

    @Getter
    final OAuth20Service service = new ServiceBuilder(clientId)
            .apiSecret(clientSecret)
            .defaultScope(customScope)
            .responseType("token")
            .callback("http://localhost:8080/authorization/returnCodeVK")
            .build(VkontakteApi.instance());

    @Getter
    final String authorizationUrl = service.createAuthorizationUrlBuilder()
            .scope(customScope)
            .build();

    public OAuth2AccessToken toGetTokenVK(String code) throws InterruptedException, ExecutionException, IOException {
        return service.getAccessToken(AccessTokenRequestParams.create(code).scope(customScope));
    }

    public User toCreateUser(OAuth2AccessToken token) throws InterruptedException, ExecutionException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, protectedResourceUrl);
        service.signRequest(token, request);
        User user = null;

        try (Response response = service.execute(request)) {
            JSONObject jsonObj = new JSONObject(response.getBody());
            JSONArray jArray = jsonObj.getJSONArray("response");
            String password = jArray.getJSONObject(0).optString("id");
            String firstName = jArray.getJSONObject(0).optString("first_name");
            String lastName = jArray.getJSONObject(0).optString("last_name");
            String email = jArray.getJSONObject(0).optString("email");
            user = new User(firstName, lastName, email, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
