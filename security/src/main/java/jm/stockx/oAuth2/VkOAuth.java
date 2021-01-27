package jm.stockx.oAuth2;

import com.github.scribejava.core.oauth.OAuth20Service;
import jm.stockx.RoleService;
import jm.stockx.UserService;
import jm.stockx.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VkOAuth {
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

    public VkOAuth(UserService userService, RoleService roleService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


}
