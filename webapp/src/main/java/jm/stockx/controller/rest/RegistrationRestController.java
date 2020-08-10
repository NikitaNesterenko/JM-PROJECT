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

import javax.validation.Valid;

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
    public Response<?> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userDto == null) {
            log.info("Ошибка регистрации пользователя. Не корректные регистрационные данные.");
            return Response.error(HttpStatus.BAD_REQUEST, "data.empty");
        }
        String firstName = userDto.getFirstName();
        if (firstName == null || firstName.length() < 1 || firstName.length() > 50) {
            log.info("!Ошибка регистрации пользователя. firstName не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "data.firstName");
        }
        String lastName = userDto.getLastName();
        if (lastName == null || lastName.length() < 1 || lastName.length() > 50) {
            log.info("Ошибка регистрации пользователя. lastName не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "data.lastName");
        }
        String password = userDto.getPassword();
        if (password == null || password.length() < 8) {
            log.info("Ошибка регистрации пользователя. password не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, "data.password");
        }
        String email = userDto.getEmail();
        String emailError = "data.email";
        if (email == null || email.indexOf('@') == -1) {
            log.info("Ошибка регистрации пользователя. email не соответствует условию.");
            return Response.error(HttpStatus.BAD_REQUEST, emailError + email);
        }
        String emailNotNull = "data.email.notenull";
        if (userService.getUserByEmail(email) != null) {
            log.info("Ошибка регистрации пользователя. Почтовый адрес {} уже используется.", email);
            return Response.error(HttpStatus.BAD_REQUEST, emailNotNull + email);
        }
        String localeTag = userDto.getLocaleTag();
        if (localeTag == null || !localeTag.contains("ru") & !localeTag.contains("en")) {
            userDto.setLocaleTag("en");
        }

        User user = new User(userDto);
        userService.createUser(user);
        log.info("Пользователь {} успешно зарегистрирован", user);
        return Response.ok(user);
    }
}