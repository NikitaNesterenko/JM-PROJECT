package jm.stockx.rest_controller;


import jm.stockx.UserExistsException;
import jm.stockx.UserRegistrationService;
import jm.stockx.UserService;
import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.util.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {
    private final UserRegistrationService userRegistrationService;

    // TODO: Не используется. Потенциально можно убрать.
    private final UserService userService;

    public RegistrationRestController(UserRegistrationService service, UserService userService) {
        this.userRegistrationService = service;
        this.userService = userService;
    }

    @PostMapping
    public Response<Void> registrationNewUser(@RequestBody UserRegistrationDto user) throws UserExistsException {
        userRegistrationService.registrationUser(user);
        return Response.ok().build();
    }
}