package jm.stockx.rest_controller;

import jm.stockx.AuthorizationException;
import jm.stockx.UserNotFoundException;
import jm.stockx.UserService;
import jm.stockx.dto.UserTokenDto;
import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.entity.Role;
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
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider jwtTokenProvider,
                                        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody UserLoginDto loginUser) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getEmail(),
                            loginUser.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String username = loginUser.getEmail();
            Role role = null;
            try {
                role = userService.getUserByEmail(username).getRole();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }

            try {
                return Response.ok(
                        new UserTokenDto(
                                userService.getUserByEmail(loginUser.getEmail()),
                                jwtTokenProvider.createToken(username, role)
                        )
                );
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        } catch (AuthenticationException e) {
            throw new AuthorizationException();
        }
        return null;
    }
}
