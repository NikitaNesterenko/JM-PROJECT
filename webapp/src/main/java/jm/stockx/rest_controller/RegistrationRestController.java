package jm.stockx.rest_controller;


import jm.stockx.UserRegistrationService;
import jm.stockx.UserService;
import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {

    private final UserRegistrationService userRegistrationService;

    private final UserService userService;

    public RegistrationRestController(UserRegistrationService service,UserService userService ) {
        this.userRegistrationService = service;
        this.userService = userService;
    }

    @PostMapping
    public Response<?> registrationNewUser(@RequestBody UserRegistrationDto user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return Response.error(HttpStatus.BAD_REQUEST, "Пользователь с таким Email уже существует");
        }
        userRegistrationService.registrationUser(user);

        return Response.ok().build();
    }

}
