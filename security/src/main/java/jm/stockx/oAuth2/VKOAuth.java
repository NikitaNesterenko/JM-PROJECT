package jm.stockx.oAuth2;

import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import jm.stockx.RoleService;
import jm.stockx.UserNotFoundException;
import jm.stockx.UserService;
import jm.stockx.entity.User;
import jm.stockx.jwt.JwtTokenProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class VKOAuth {
    private OAuth20Service auth20Service;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${vk.client_id}")
    private String client_id;

    @Value("${vk.redirect_uri}")
    private String redirect_uri;

    @Value("${vk.scope}")
    private String scope;

    @Value("${vk.response_type}")
    private String response_type;

    @Value("${vk.clientSecret}")
    private String clientSecret;

    @Value("@{vk.basicPassword}")
    private String basicPassword;

    User user;

    public VKOAuth(UserService userService, RoleService roleService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostConstruct
    private void init() {
        auth20Service = new ServiceBuilder(client_id)
                .apiSecret(clientSecret)
                .defaultScope(scope)
                .responseType(response_type)
                .callback(redirect_uri)
                .build(VkontakteApi.instance());
    }

    public String getAuthorizeUrl() {
        return auth20Service.getAuthorizationUrl();
    }

    public OAuth2AccessToken getOAuthToken(String code) throws InterruptedException, ExecutionException, IOException {
        return auth20Service.getAccessToken(code);
    }

    public String getVkUserToken(OAuth2AccessToken token) throws InterruptedException, ExecutionException, IOException, UserNotFoundException {

        OAuthRequest request = new OAuthRequest(Verb.GET,  "https://api.vk.com/method/users.get?v=5.52");
        auth20Service.signRequest(token, request);
        Response response = auth20Service.execute(request);

        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONArray jArray = jsonObj.getJSONArray("response");
        String firstName = jArray.getJSONObject(0).optString("first_name");
        String lastName = jArray.getJSONObject(0).optString("last_name");
        String email = jArray.getJSONObject(0).optString("email");

        if (userService.isUserExistByEmail(email)) {
            try {
                user = userService.getUserByEmail(email);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            user = new User(firstName, lastName, email, basicPassword);
            user.setRole(roleService.getRole("ROLE_USER"));
            userService.createUser(user);
        }
        return jwtTokenProvider.createToken(email, user.getRole());
    }


}
