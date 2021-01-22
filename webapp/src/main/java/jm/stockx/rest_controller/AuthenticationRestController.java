package jm.stockx.rest_controller;

import jm.stockx.AuthorizationException;
import jm.stockx.UserDetailsServiceImpl;
import jm.stockx.dto.UserTokenDto;
import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.util.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationRestController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Response<UserTokenDto> login(@RequestBody UserLoginDto loginUser) {
        try {
            String generatedToken = userDetailsService.authenticateUser(loginUser);

            UserTokenDto userTokenForResponse = new UserTokenDto(generatedToken);
            return Response.ok(userTokenForResponse);
        } catch (AuthenticationException e) {
            throw new AuthorizationException();
        }
    }
}