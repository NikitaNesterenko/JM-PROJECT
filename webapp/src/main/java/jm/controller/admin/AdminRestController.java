package jm.controller.admin;

import jm.User;
import jm.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin")
public class AdminRestController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        logger.info("Получен список пользователей. Всего {} записей.", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createUser(User user) {
        String username = user.getUsername();
        if (userService.getUserByUserName(username) != null) {
            logger.warn("Пользователь {} уже существует в базе", username);
            return new ResponseEntity<>("This user already exists in database", HttpStatus.CONFLICT);
        }
        userService.createUser(user);
        logger.info("Пользователь {} успешно создан", username);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<?> updateUser(User user) {
        String username = user.getUsername();
        if (userService.getUserByUserName(username) == null) {
            logger.warn("Пользователь {} в базе не найден", username);
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userService.updateUser(user);
        logger.info("Пользователь {} успешно обновлен", username);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<?> deleteUser(Long id) {
        if (userService.getUserById(id) == null) {
            logger.warn("Пользователь с id = {} в базе не найден", id);
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        logger.info("Пользователь с id = {} успешно удален", id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
