package jm.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.User;
import jm.UserService;
import jm.component.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin")
@Tag(name = "admin", description = "Admin API")
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
        if (users != null) {
            return Response.ok(users);
        }
        return Response.error(HttpStatus.BAD_REQUEST, "No users found");
    }

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
    @PostMapping(value = "/createUser")
    public Response<?> createUser(User user) {
        if (userService.getUserByUserName(user.getUsername()) != null) {
            return Response.error(HttpStatus.BAD_REQUEST, "Error when creating a user");
        }
        userService.createUser(user);
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
        if (userService.getUserByUserName(user.getUsername()) == null) {
            return Response.error(HttpStatus.BAD_REQUEST, "User not found");
        }
        userService.updateUser(user);
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
            return Response.error(HttpStatus.BAD_REQUEST, "User not found"); //нужно ли тут сообщение?
        }
        userService.deleteUser(id);
        return Response.ok(true); //почему тут так? почему нет статуса и строки?
    }
}
