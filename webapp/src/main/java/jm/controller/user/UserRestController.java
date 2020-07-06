package jm.controller.user;

import jm.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/users")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @GetMapping(value = "/getUser")
    public ResponseEntity<User> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Получен пользователь: {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
