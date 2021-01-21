package jm.stockx;

import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserDetailsServiceImpl(UserService userService,
                                  AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (user == null) {
            logger.info("Пользователь {} не найден", email);
            throw new UsernameNotFoundException("Unknown user: " + email);
        }
        return user;
    }

    public Authentication authenticateUser(@RequestBody UserLoginDto loginUser) {
        try {
            Authentication authForCreation =
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword());
            final Authentication authentication = authenticationManager.authenticate(authForCreation);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
        } catch (AuthenticationException e) {
            e.getMessage();
            throw new AuthorizationException();
        }
    }
}