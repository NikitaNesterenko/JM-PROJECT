package jm.stockx.oauth2;

import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
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

    @Value("${vk.clientSecret}")
    private String clientSecret;

    @Value("${vk.redirect_uri}")
    private String redirect_uri;

    @Value("${vk.scope}")
    private String scope;

    @Value("${vk.response_type}")
    private String response_type;

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

    public String getVkUserToken(String code) throws InterruptedException, ExecutionException, IOException, UserNotFoundException {

        OAuthRequest request = new OAuthRequest(Verb.GET,  "https://oauth.vk.com/access_token");
        request.addParameter("client_id", client_id);
        request.addParameter("client_secret", clientSecret);
        request.addParameter("redirect_uri", redirect_uri);
        request.addParameter("code",code);
        Response response = auth20Service.execute(request);

        JSONObject jsonObj = new JSONObject(response.getBody());
        String token = jsonObj.optString("access_token");
        String email = jsonObj.optString("email");

        OAuthRequest request1 = new OAuthRequest(Verb.GET,  "https://api.vk.com/method/users.get?v=5.52");
        auth20Service.signRequest(token, request1);
        Response response1 = auth20Service.execute(request1);

        JSONObject jsonObj1 = new JSONObject(response1.getBody());
        JSONArray jArray = jsonObj1.getJSONArray("response");
        String firstName = jArray.getJSONObject(0).optString("first_name");
        String lastName = jArray.getJSONObject(0).optString("last_name");
//    //  TODO
        if (userService.isUserExistByEmail(email)) {
            user = userService.getUserByEmail(email);
        } else {
            user = new User(firstName, lastName, email, basicPassword);
            user.setRole(roleService.getRole("ROLE_USER"));
            userService.createUser(user);
        }
        return jwtTokenProvider.createToken(email, user.getRole());
    }
}
