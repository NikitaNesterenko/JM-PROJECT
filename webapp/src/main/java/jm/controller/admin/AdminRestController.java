package jm.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.User;
import jm.UserService;
import jm.component.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin")
@Tag(name = "admin", description = "Admin API")
public class AdminRestController {

    private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            operationId = "getAllUsers",
            summary = "Get all users",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = User.class)
                            ),
                            description = "OK: user list received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no users found")
            })
    public Response<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        logger.info("Получен список пользователей. Всего {} записей.", users.size());
        return Response.ok(users);
    }

    @PostMapping(value = "/createUser")
    @Operation(
            operationId = "createUser",
            summary = "Create user",
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(responseCode = "200", description = "OK: user created"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: user not created")
            })
    public Response<?> createUser(User user) {
        String username = user.getUsername();
        if (userService.getUserByUserName(username) != null) {
            logger.warn("Пользователь {} уже существует в базе", username);
            return Response.error(HttpStatus.BAD_REQUEST, "Error when creating a user");
        }
        userService.createUser(user);
        logger.info("Пользователь {} успешно создан", username);
        return Response.ok(HttpStatus.OK, "User created successfully");
    }

    @PutMapping(value = "/updateUser")
    @Operation(
            operationId = "updateUser",
            summary = "Update user",
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(responseCode = "200", description = "OK: user updated successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update user")
            })
    public Response<?> updateUser(User user) {
        String username = user.getUsername();
        if (userService.getUserByUserName(user.getUsername()) == null) {
            logger.warn("Пользователь {} в базе не найден", username);
            return Response.error(HttpStatus.BAD_REQUEST, "User not found");
        }
        userService.updateUser(user);
        logger.info("Пользователь {} успешно обновлен", username);
        return Response.ok().build();
    }

    @DeleteMapping(value = "/deleteUser")
    @Operation(
            operationId = "deleteUser",
            summary = "Delete user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: user deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT FOUND: no user with such id")
            }
    )
    public Response<Boolean> deleteUser(Long id) {
        if (userService.getUserById(id) == null) {
            logger.warn("Пользователь с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "User not found");
        }
        userService.deleteUser(id);
        logger.info("Пользователь с id = {} успешно удален", id);
        return Response.ok().build();
    }
}
