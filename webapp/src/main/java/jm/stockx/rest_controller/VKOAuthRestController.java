package jm.stockx.rest_controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import jm.stockx.oAuth2.VKOAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class VKOAuthRestController {

        private final VKOAuth vkOAuth;

        @Value("${jwt.cookieName}")
        private String cookieName;

        @Value("${vk.successUrl}")
        private String successUrl;

        @Value("${vk.authorizeUrl}")
        private String authorizeUrl;

        public VKOAuthRestController(VKOAuth vkOAuth) {
            this.vkOAuth = vkOAuth;
        }

        @GetMapping("/url/vk")
        public String authUrl() {
            return vkOAuth.getAuthorizeUrl();
        }

        @GetMapping("/vk")
        public void vkAuth(@RequestParam String code, HttpServletResponse request) {
            try {
                OAuth2AccessToken oAuth2Token = vkOAuth.getOAuthToken(code);
                String jwtToken = vkOAuth.getVkUserToken(oAuth2Token);

                request.addCookie(new Cookie(cookieName, jwtToken));
                request.sendRedirect(successUrl);

            } catch (Exception e) {
                System.out.println(e.getMessage());

                try {
                    request.sendRedirect(authorizeUrl);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }


