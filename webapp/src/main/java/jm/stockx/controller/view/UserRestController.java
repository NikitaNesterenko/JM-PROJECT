package jm.stockx.controller.view;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.MailService;
import jm.stockx.UserService;
import jm.stockx.dto.UserDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rest/api/users")
@Tag(name = "user", description = "User API")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public UserRestController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

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

    @GetMapping(value = "/password-recovery/{email}")
    @Operation(
            operationId = "sendRecoveryLinkToEmail",
            summary = "Send password recovery token to email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: recovery password token was send"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to send password recovery token")
            })
    public Response<?> sendRecoveryLinkToEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            mailService.sendRecoveryLinkToUser(user);
            logger.info("Отправлен запрос на восстановление пароля пользователю с email = {}", email);
            return Response.ok().build();
        }
        logger.warn("Не определен email {} для восстаноления пароля", email);
        return Response.error(HttpStatus.BAD_REQUEST, "User with such mail does not exist");
    }

    @PostMapping(value = "/password-recovery")
    @Operation(
            operationId = "passwordRecovery",
            summary = "Recover password",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: password recovered"),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: unable to recover password")
            })
    public Response<?> passwordRecovery(@RequestParam(name = "token") String link,
                                        @RequestParam(name = "password") String newPassword) {

        if (mailService.changePasswordByToken(link, newPassword)) {
            logger.info("Пароль по адресу {} восстановлен", link);
            return Response.ok().build();
        }
        logger.warn("Ошибка восстановления пароля по адресу {}", link);
        return Response.error(HttpStatus.BAD_REQUEST, "Error recovery password");
    }

    @GetMapping(value = "/registration/{code}")
    @Operation(
            operationId = "activateAccount",
            summary = "Token received to email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: account avtivated"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to activate account")
            })
    public Response<?> activateAccountByToken(@PathVariable("code") String code) {

        if (mailService.activateAccountByToken(code)) {
            logger.info("Аккаунт активирован = {}", code);
            return Response.ok().build();
        }
        logger.warn("Не определен код активации {} для восстаноления пароля", code);
        return Response.error(HttpStatus.BAD_REQUEST, "User with such activation code does not exist");
    }
}