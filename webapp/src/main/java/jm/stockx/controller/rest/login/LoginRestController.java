package jm.stockx.controller.rest.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.JwtUtil;
import jm.stockx.UserService;
import jm.stockx.dto.UserDTO;
import jm.stockx.dto.UserLoginDTO;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/auth/login")
@Tag(name = "login", description = "Login API")
@Slf4j
public class LoginRestController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    public LoginRestController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @Operation(
            operationId = "signIn",
            summary = "Sign-in user",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class)
                            ),
                            description = "OK: user logged in"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: user was not logged in")
            })
    public Response<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            String email = userLoginDTO.getEmail();
            String password = userLoginDTO.getPassword();

            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(authReq); // проверка аутентификации
            SecurityContextHolder.getContext().setAuthentication(authentication); //устанавливается контекст безопасности для пользователя, прошедшего аутентификацию

            User user = userService.getUserByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }
            String token = jwtUtil.generateToken(user);

            UserDTO userDTO = new UserDTO(user);
            ModelMap response = new ModelMap();
            response.addAttribute("userDTO", userDTO);
            response.addAttribute("token", token);

            return Response.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
