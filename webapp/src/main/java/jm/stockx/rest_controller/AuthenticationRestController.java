package jm.stockx.rest_controller;

import jm.stockx.AuthorizationException;
import jm.stockx.UserDetailsServiceImpl;
import jm.stockx.UserNotFoundException;
import jm.stockx.UserService;
import jm.stockx.dto.UserTokenDto;
import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.entity.Role;
import jm.stockx.entity.User;
import jm.stockx.jwt.JwtTokenProvider;
import jm.stockx.util.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationRestController(JwtTokenProvider jwtTokenProvider,
                                        UserService userService,
                                        UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Response<UserTokenDto> login(@RequestBody UserLoginDto loginUser) {
        try {
            userDetailsService.authenticateUser(loginUser);

            User foundUser = userService.getUserByEmail(loginUser.getEmail());
            Role foundRole = foundUser.getRole();
            String createdToken = jwtTokenProvider.createToken(foundUser.getEmail(), foundRole);

            UserTokenDto userTokenForResponse = new UserTokenDto(foundUser, createdToken);
            return Response.ok(userTokenForResponse);
        } catch (AuthenticationException e) {
            throw new AuthorizationException();
        } catch (UserNotFoundException e) {
            e.getMessage();
        }
        return null;
    }
}
