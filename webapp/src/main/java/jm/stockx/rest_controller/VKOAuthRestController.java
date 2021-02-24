package jm.stockx.rest_controller;

import jm.stockx.oauth2.VKOAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
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
        public void vkAuth(@RequestParam String code, HttpServletResponse response) {
            try {

                String jwtToken = vkOAuth.getVkUserToken(code);

                response.addCookie(new Cookie(cookieName, jwtToken));
                response.sendRedirect(successUrl);

            } catch (Exception e) {
                log.error(e.getMessage());
                try {
                    response.sendRedirect(authorizeUrl);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    log.error(ioException.getMessage());
                }
            }
        }
    }


