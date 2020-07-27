package jm.stockx.controller.rest.auth;

import com.github.scribejava.apis.vk.VKOAuth2AccessToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import jm.stockx.auth.GoogleAuthorization;
import jm.stockx.auth.TelegramAuthorisation;
import jm.stockx.dto.TelegramUserDTO;
import jm.stockx.entity.User;
import jm.stockx.UserService;
import jm.stockx.auth.VkAuthorisation;
import jm.stockx.util.Response;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/authorization")
public class AuthRestController {
    private final Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    private GoogleAuthorization googleAuthorization;
    private VkAuthorisation vkAuthorization;
    private UserService userService;
    private TelegramAuthorisation telegramAuthorisation;

    @Autowired
    public AuthRestController(GoogleAuthorization googleAuthorization,
                              VkAuthorisation vkAuthorisation,
                              UserService userService,
                              TelegramAuthorisation telegramAuthorisation) {
        this.googleAuthorization = googleAuthorization;
        this.vkAuthorization = vkAuthorisation;
        this.userService = userService;
        this.telegramAuthorisation = telegramAuthorisation;
    }

    @GetMapping("/vkAuth")
    public Response<Object> toVk() throws URISyntaxException {
        URI vk = new URI(vkAuthorization.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(vk);
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/returnCodeVK")
    public Response<Object> getCodeThird(@RequestParam String code) throws
            InterruptedException, ExecutionException, IOException, URISyntaxException {
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

    @GetMapping("/googleAuth")
    public Response<?> toGoogle() throws URISyntaxException {
        URI google = new URI(googleAuthorization.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(google);
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/returnCodeGoogle")
    public Response<?> getCodeGoogle(@RequestParam String code) throws
            InterruptedException, ExecutionException, IOException, URISyntaxException {
        OAuth2AccessToken token = googleAuthorization.getGoogleOAuth2AccessToken(code);
        String email = ((VKOAuth2AccessToken) token).getEmail();
        User currentUser = googleAuthorization.getGoogleUser(token, email);
        userService.login(currentUser.getUsername(), currentUser.getPassword(), currentUser.getAuthorities());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/index"));
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/telegramAuth")
    public Response<?> telegramAuth(@RequestParam String id, String first_name,
                                         String last_name, String username, String photo_url,
                                         String auth_date, String hash) throws URISyntaxException {

        TelegramUserDTO telegramUserDTO = new TelegramUserDTO(id, first_name, last_name, username,
                photo_url, auth_date, hash);

        logger.info("Telegram auth!!!");
        logger.info(telegramUserDTO.toString());

        HttpHeaders httpHeaders = new HttpHeaders();

        if (telegramAuthorisation.isTelegramAccountDataRight(telegramUserDTO)) {
            User telegramUser = telegramAuthorisation.toTelegramUser(telegramUserDTO);
            userService.login(telegramUser.getUsername(), telegramUser.getPassword(), telegramUser.getAuthorities());

            httpHeaders.setLocation(new URI("/index"));
            Response.BodyBuilder bodyBuilder = Response.ok();
            return bodyBuilder.headers(httpHeaders).build();
        } else {
            httpHeaders.setLocation(new URI("/login"));
            Response.BodyBuilder bodyBuilder = Response.error(HttpStatus.UNAUTHORIZED);
            return bodyBuilder.headers(httpHeaders).build();
        }
    }
}
