package jm.stockx;

import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserService userService;

    private final MailService mailService;

    private final PasswordGeneratorServiceImpl passwordGeneratorService;

    public UserRegistrationServiceImpl(UserService userService, MailService mailService, PasswordGeneratorServiceImpl passwordGeneratorService) {
        this.userService = userService;
        this.mailService = mailService;
        this.passwordGeneratorService = passwordGeneratorService;
    }

    @Override
    public Response<?> registrationUser(UserRegistrationDto user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return Response.ok(HttpStatus.BAD_REQUEST, user);
        } else if (user.getFirstName().isEmpty() || user.getFirstName().isBlank()) {
            return Response.ok(HttpStatus.BAD_REQUEST, user);
        } else if (user.getLastName().isEmpty() || user.getLastName().isBlank()) {
            return Response.ok(HttpStatus.BAD_REQUEST, user);
        }

        String password = passwordGeneratorService.generatePassword(8);

        userService.createUser(new User(user.getFirstName(), user.getLastName(), user.getEmail(), password));

        mailService.sendSimpleMessage(
                user.getEmail(),
                "Registration success",
                "Thank you for your registration\n" +
                        "your password:\n" + password
        );

        return Response.ok(user);

    }
}
