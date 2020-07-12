package jm.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.User;
import jm.component.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/users")
@Tag(name = "user", description = "User API")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @GetMapping(value = "/getLoggedInUser")
    @Operation(
            operationId = "getLoggedInUser",
            summary = "Get logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            ),
                            description = "OK: get logged-in user"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no logged-in user found")
            })
    public Response<User> getLoggedInUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Получен пользователь: {}", user.getUsername());
        return Response.ok(user);
    }
}
