package jm.controller;

import com.github.scribejava.apis.vk.VKOAuth2AccessToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import jm.User;
import jm.UserService;
import jm.auth.VkAuthorisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/authorization")
public class AuthController {

    private VkAuthorisation vkontakte;
    private UserService userService;

    @Autowired
    public AuthController(VkAuthorisation vkontakte, UserService userService) {
        this.vkontakte = vkontakte;
        this.userService = userService;
    }

    @GetMapping("/vkAuth")
    public ResponseEntity<Object> toVk() throws URISyntaxException {
        URI vk = new URI(vkontakte.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(vk);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/returnCodeVK")
    public ResponseEntity<Object> getCodeThird(@RequestParam String code) throws InterruptedException, ExecutionException, IOException, URISyntaxException {
        OAuth2AccessToken token = vkontakte.toGetTokenVK(code);
        String email = ((VKOAuth2AccessToken) token).getEmail();
        User currentUser = vkontakte.toCreateUser(token, email);
        if (userService.getUserByUserName(email) == null) {
            userService.createUser(currentUser);
        }
        userService.login(currentUser.getUsername(), currentUser.getPassword(), currentUser.getAuthorities());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/index"));
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
