package jm.stockx.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.vk.VKOAuth2AccessToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import io.jsonwebtoken.Claims;
import jm.stockx.UserService;
import jm.stockx.apple.AppleIdAuthorization;
import jm.stockx.auth.GoogleAuthorization;
import jm.stockx.auth.VkAuthorisation;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/authorization")
public class AuthRestController {

    private final UserService userService;
    private final GoogleAuthorization googleAuthorization;
    private final VkAuthorisation vkAuthorization;
    private final AppleIdAuthorization appleIdAuthorization;

    public AuthRestController(UserService userService,
                              GoogleAuthorization googleAuthorization,
                              VkAuthorisation vkAuthorisation,
                              AppleIdAuthorization appleIdAuthorization) {
        this.userService = userService;
        this.vkAuthorization = vkAuthorisation;
        this.googleAuthorization = googleAuthorization;
        this.appleIdAuthorization = appleIdAuthorization;
    }

    /* VK */
    @GetMapping("/vkAuth")
    public Response<Object> toVk() throws URISyntaxException {
        URI vk = new URI(vkAuthorization.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(vk);
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/returnCodeVK")
    public Response<Object> getCodeThird(@RequestParam String code) throws InterruptedException, ExecutionException, IOException, URISyntaxException {
        OAuth2AccessToken token = vkAuthorization.toGetTokenVK(code);
        String email = ((VKOAuth2AccessToken) token).getEmail();
        User currentUser = vkAuthorization.toCreateUser(token, email);
        if (userService.getUserByUserName(email) == null) {
            userService.createUser(currentUser);
        }
        userService.login(currentUser.getUsername(), currentUser.getPassword(), currentUser.getAuthorities());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/index"));
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    /* APPLE */
    @GetMapping("/appleAuth")
    public Response<Object> toApple() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(appleIdAuthorization.getAuthorizationUrl()));
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/appleReturnCode")
    public Response<Object> authorizationResponseFromApple(@RequestParam("code") String code,
                                                           @RequestParam("id_token") String idToken,
                                                           @RequestParam("user") String jsonUser) throws Exception {
        Claims claims = appleIdAuthorization.getClimesFromApple(code, getKeyIdFromIdToken(idToken));
        User currentUser = findOrRegisterNewUser(jsonUser, claims);
        userService.login(currentUser.getUsername(), currentUser.getPassword(), currentUser.getAuthorities());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/index"));
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    private User findOrRegisterNewUser(String jsonUser, Claims claims) throws IOException {
        String appleIdentifier = claims.get("sub", String.class);
        User currentUser = userService.getUserByAppleUserId(appleIdentifier);
        if (currentUser == null) {
            String appleEmail = claims.get("email", String.class);
            Map<String, String> userInfo = new ObjectMapper().readValue(jsonUser, HashMap.class);
            if (appleEmail == null) {
                appleEmail = userInfo.get("email");
            }
            currentUser = userService.getUserByEmail(appleEmail);
            if (currentUser != null) {
                currentUser.setAppleUserId(appleIdentifier);
                userService.updateUser(currentUser);
            } else {
                Map<String, String> userInfoDetails = new ObjectMapper().readValue(userInfo.get("name"), HashMap.class);
                currentUser = new User();
                currentUser.setFirstName(userInfoDetails.get("firstName"));
                currentUser.setLastName(userInfoDetails.get("lastName"));
                currentUser.setUsername(appleEmail);
                currentUser.setEmail(appleEmail);
                currentUser.setAppleUserId(appleIdentifier);
                userService.createUser(currentUser);
            }
        }
        return currentUser;
    }

    private String getKeyIdFromIdToken(String idToken) throws IOException {
        String headerIdToken = idToken.split("\\.")[0];
        String decodedHeader = new String(Base64.getDecoder().decode(headerIdToken));
        Map<String, String> result = new ObjectMapper().readValue(decodedHeader, HashMap.class);
        return result.get("kid");
    }

    /* GOOGLE */
    @GetMapping("/googleAuth")
    public Response<?> toGoogle() throws URISyntaxException {
        URI google = new URI(googleAuthorization.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(google);
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/returnCodeGoogle")
    public Response<?> getCodeGoogle(@RequestParam String code) throws InterruptedException, ExecutionException, IOException, URISyntaxException {
        OAuth2AccessToken token = googleAuthorization.getGoogleOAuth2AccessToken(code);
        String email = ((VKOAuth2AccessToken) token).getEmail();
        User currentUser = googleAuthorization.getGoogleUser(token, email);
        userService.login(currentUser.getUsername(), currentUser.getPassword(), currentUser.getAuthorities());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/index"));
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }
}
