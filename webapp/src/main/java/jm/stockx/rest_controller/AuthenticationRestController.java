package jm.stockx.rest_controller;

import jm.stockx.AuthorizationException;
import jm.stockx.UserDetailsServiceImpl;
import jm.stockx.dto.UserTokenDto;
import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    private final UserDetailsServiceImpl userDetailsService;
    private final RestCustomHeaderVerificator restCustomHeaderVerificator;

    public AuthenticationRestController(UserDetailsServiceImpl userDetailsService, RestCustomHeaderVerificator restCustomHeaderVerificator) {
        this.userDetailsService = userDetailsService;
        this.restCustomHeaderVerificator = restCustomHeaderVerificator;
    }

    @PostMapping("/login")
    public Response<UserTokenDto> login(@RequestBody UserLoginDto loginUser, HttpServletRequest httpServletRequest) {
        try {
            String generatedToken = userDetailsService.authenticateUser(loginUser);

            UserTokenDto userTokenForResponse = new UserTokenDto(generatedToken);
            if(restCustomHeaderVerificator.getVerificationValue(httpServletRequest)){
                return Response.ok(userTokenForResponse);
            } else{
                return Response.error(HttpStatus.LOCKED, " Blocked by HeaderVerification");
            }
        } catch (AuthenticationException e) {
            throw new AuthorizationException();
        }
    }
}