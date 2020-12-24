package jm.stockx.oAuth2;

import com.github.scribejava.apis.GoogleApi20;
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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class GoogleOAuth {

    private OAuth20Service auth20Service;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${google.basicPassword}")
    private String basicPassword;

    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.callbackUrl}")
    private String callbackUrl;

    private User user;

    public GoogleOAuth(UserService userService, RoleService roleService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostConstruct
    private void init() {
        auth20Service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope("profile email")
                .callback(callbackUrl)
                .build(GoogleApi20.instance());
    }


    public String getAuthorizeUrl() {
        return auth20Service.getAuthorizationUrl();
    }

    public OAuth2AccessToken getOAuthToken(String code) throws InterruptedException, ExecutionException, IOException {
        return auth20Service.getAccessToken(code);
    }

    public String getGoogleUserToken(OAuth2AccessToken token) throws InterruptedException, ExecutionException, IOException, UserNotFoundException {

        OAuthRequest request = new OAuthRequest(Verb.GET,  "https://www.googleapis.com/oauth2/v1/userinfo?alt=json");
        auth20Service.signRequest(token, request);
        Response response = auth20Service.execute(request);

        JSONObject jsonObj = new JSONObject(response.getBody());

        String firstName = jsonObj.optString("given_name");
        String lastName = jsonObj.optString("family_name");
        String email = jsonObj.optString("email");

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
