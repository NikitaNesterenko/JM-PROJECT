package jm.stockx;

import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.entity.User;
import jm.stockx.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserDetailsServiceImpl(UserService userService,
                                  AuthenticationManager authenticationManager,
                                  JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            log.info("Пользователь {} не найден", email);
            throw new UsernameNotFoundException("Unknown user: " + email);
        } else {
            return user;
        }
    }

    public String authenticateUser(UserLoginDto loginUser) {
        Authentication authForCreation =
                new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword());
        final Authentication authentication = authenticationManager.authenticate(authForCreation);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return getToken(authentication);
    }

    private String getToken(Authentication authForTokenGeneration) {
        User details = (User) authForTokenGeneration.getPrincipal();
        return jwtTokenProvider.createToken(details.getEmail(), details.getRole());
    }
}