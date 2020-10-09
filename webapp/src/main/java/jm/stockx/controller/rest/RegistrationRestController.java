package jm.stockx.controller.rest;


import jm.stockx.UserRegistrationService;
import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.util.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationRestController {

    private final UserRegistrationService service;

    public RegistrationRestController(UserRegistrationService service) {
        this.service = service;
    }

    @PostMapping
    public Response<?> registrationNewUser(@RequestBody UserRegistrationDto user) {
        return service.registrationUser(user);
    }

}
