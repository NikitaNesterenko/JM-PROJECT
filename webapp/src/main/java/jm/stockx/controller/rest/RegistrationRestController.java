package jm.stockx.controller.rest;

import jm.stockx.UserService;
import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@Slf4j
public class RegistrationRestController {
    private final UserService userService;

    @Autowired
    public RegistrationRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Response<?> registerUser(@RequestBody UserDto userDto) {
        if (userDto == null) {
            log.info("Ошибка регистрации пользователя. Не корректные регистрационные данные.");
            return Response.error(HttpStatus.BAD_REQUEST, "Invalid registration data");
        }
        String firstName = userDto.getFirstName();
        if (firstName == null || firstName.length() < 1 || firstName.length() > 50) {
            log.info("!Ошибка регистрации пользователя. firstName не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "!first name must be between 1 and 50 characters long");
        }
        String lastName = userDto.getLastName();
        if (lastName == null || lastName.length() < 1 || lastName.length() > 50) {
            log.info("Ошибка регистрации пользователя. lastName не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "last name must be between 1 and 50 characters long");
        }
        String password = userDto.getPassword();
        if (password == null || password.length() < 8) {
            log.info("Ошибка регистрации пользователя. password не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "At least 8 characters");
        }
        String email = userDto.getEmail();
        if (email == null || email.indexOf('@') == -1) {
            log.info("Ошибка регистрации пользователя. email не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "error in email - email format validation failed: " + email);
        }
        if (userService.getUserByEmail(email) != null) {
            log.info("Ошибка регистрации пользователя. Почтовый адрес {} уже используется.", userDto.getEmail());
            return Response.error(HttpStatus.BAD_REQUEST, "There is already an account associated with this email address. Please login using your existing account");
        }

        User user = new User(userDto);
        userService.createUser(user);
        log.info("Пользователь {} успешно зарегистрирован", user);
        return Response.ok(user);
    }
}