package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.UserService;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin")
@Tag(name = "admin", description = "Admin API")
@Slf4j
public class AdminRestController {
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
        log.info("Получен список пользователей. Всего {} записей.", users.size());
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
    public Response<?> createUser(@Valid @RequestBody User user) {
        String username = user.getUsername();
        if (userService.getUserByUserName(username) != null) {
            log.warn("Пользователь {} уже существует в базе", username);
            return Response.error(HttpStatus.BAD_REQUEST, "Error when creating a user");
        }
        userService.createUser(user);
        log.info("Пользователь {} успешно создан", username);
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
    public Response<?> updateUser(@Valid @RequestBody User user) {
        String username = user.getUsername();
        if (userService.getUserByUserName(user.getUsername()) == null) {
            log.warn("Пользователь {} в базе не найден", username);
            return Response.error(HttpStatus.BAD_REQUEST, "User not found");
        }
        userService.updateUser(user);
        log.info("Пользователь {} успешно обновлен", username);
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
            log.warn("Пользователь с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "User not found");
        }
        userService.deleteUser(id);
        log.info("Пользователь с id = {} успешно удален", id);
        return Response.ok().build();
    }
}
