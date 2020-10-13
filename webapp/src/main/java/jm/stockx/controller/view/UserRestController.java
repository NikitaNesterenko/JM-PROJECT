package jm.stockx.controller.view;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.MailService;
import jm.stockx.RoleService;
import jm.stockx.UserService;
import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rest/api/users")
@Tag(name = "user", description = "User API")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final MailService mailService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, MailService mailService, RoleService roleService) {
        this.userService = userService;
        this.mailService = mailService;
        this.roleService = roleService;
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
    public Response<?> updateUser(@RequestBody UserPutDto userPutDto, @AuthenticationPrincipal User principal) {
        if (!userService.isUserExist(userPutDto.getId())) {
            return Response.error(HttpStatus.NOT_FOUND, "user does not exist");
        }
        if (!userService.getCurrentLoggedInUser().getId().equals(userPutDto.getId())){
            return Response.error(HttpStatus.FORBIDDEN, "user does not have permission");
        }
        userService.updateUserFromDto(userPutDto);
        return Response.ok(HttpStatus.OK).build();
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