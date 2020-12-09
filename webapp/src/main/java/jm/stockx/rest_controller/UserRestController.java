package jm.stockx.rest_controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.*;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PutMapping("/update")
    @Operation(
            operationId = "updateUser",
            summary = "update user details from UserDto",
            responses = {
                    @ApiResponse(responseCode = "400", description = "user does not exist"),
                    @ApiResponse(responseCode = "200", description = "user successfully updated  ")
            }
    )
    public Response<?> updateUser(@RequestBody UserPutDto userPutDto, @AuthenticationPrincipal User principal) throws UserNotFoundAdviceException, ForbiddenAdviceException {
        if (!userService.isUserExist(userPutDto.getId())) {
            throw new UserNotFoundAdviceException();
        }
        if (!principal.getId().equals(userPutDto.getId())){
            throw new ForbiddenAdviceException();
        }
        userService.updateUserFromDto(userPutDto);
        return Response.ok(HttpStatus.OK).build();
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
    public Response<?> sendRecoveryLinkToEmail(@PathVariable("email") String email) throws UserNotFoundAdviceException {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            mailService.sendRecoveryLinkToUser(user);
            logger.info("Отправлен запрос на восстановление пароля пользователю с email = {}", email);
            return Response.ok().build();
        }
        logger.warn("Не определен email {} для восстаноления пароля", email);
        throw new UserNotFoundAdviceException();
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
                                        @RequestParam(name = "password") String newPassword) throws RecoveryAdviceException {

        if (mailService.changePasswordByToken(link, newPassword)) {
            logger.info("Пароль по адресу {} восстановлен", link);
            return Response.ok().build();
        }
        logger.warn("Ошибка восстановления пароля по адресу {}", link);
        throw new RecoveryAdviceException();
    }

    @GetMapping(value = "/registration/{code}")
    @Operation(
            operationId = "activateAccount",
            summary = "Token received to email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: account avtivated"),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to activate account")
            })
    public Response<?> activateAccountByToken(@PathVariable("code") String code) throws UserNotFoundAdviceException {

        if (mailService.activateAccountByToken(code)) {
            logger.info("Аккаунт активирован = {}", code);
            return Response.ok().build();
        }
        logger.warn("Не определен код активации {} для восстаноления пароля", code);
        throw new UserNotFoundAdviceException();
    }
}