package service;

import jm.stockx.UserDetailsServiceImpl;
import jm.stockx.UserNotFoundException;
import jm.stockx.UserService;
import jm.stockx.dto.security.UserLoginDto;
import jm.stockx.entity.Role;
import jm.stockx.entity.User;
import jm.stockx.jwt.JwtTokenProvider;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("UserDetailsServiceImplTest must")
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private User createdUser;
    private UserLoginDto createdUserDto;
    private Authentication authForCreation;
    private String createdToken;

    @BeforeEach
    void setUp() {
        createdUser = new User(
                "Thor",
                "Odinson",
                "admin@mail.ru",
                "admin",
                "admin",
                (byte) 100,
                true,
                "ru",
                "admin@apple.id");
        createdUser.setActive(true);
        createdUser.setRole(new Role("ROLE_ADMIN"));

        List<GrantedAuthority> authorityList = List.of(new Role("ROLE_ADMIN"));

        createdUserDto = new UserLoginDto(createdUser);
        authForCreation =
                new UsernamePasswordAuthenticationToken(createdUser, "admin", authorityList);

        createdToken = "helloworld";
    }


    @Test
    @DisplayName("load user properly")
    void loadUserByUsername() throws UserNotFoundException {
        String emailAddress = "admin@mail.ru";
//        TODO
//        given(userService.getUserByEmail(emailAddress)).willReturn(createdUser);

        UserDetails foundUser = userDetailsService.loadUserByUsername(emailAddress);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(foundUser).isNotNull();
            softly.assertThat(foundUser.getUsername()).isEqualTo(emailAddress);
        });
    }

    @Test
    @SneakyThrows
    @DisplayName("throw exception loadUserByUsername if user was not found")
    void loadUserByUsernameThrowsExceptions() {
        String emailAddress = "adm12in@mail.ru";
        given(userService.getUserDtoByEmail(emailAddress))
                .willThrow(new UserNotFoundException());

        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(emailAddress));
    }

    @Test
    @DisplayName("authenticate user")
    void authenticateUser() {
        given(authenticationManager.authenticate(any(Authentication.class))).willReturn(authForCreation);
        given(createToken()).willReturn(createdToken);

        String createdToken = userDetailsService.authenticateUser(createdUserDto);

        assertNotNull(createdToken);
        assertNotEquals(createdToken, "HELLOW)RLD");
        then(authenticationManager).should().authenticate(any(Authentication.class));
        then(authenticationManager).shouldHaveNoMoreInteractions();
        then(jwtTokenProvider).should().createToken(anyString(), any(Role.class));
        then(jwtTokenProvider).shouldHaveNoMoreInteractions();
    }

    private String createToken() {
        User details = (User) authForCreation.getPrincipal();
        String token = jwtTokenProvider.createToken(details.getEmail(), details.getRole());
        return token;
    }
}
