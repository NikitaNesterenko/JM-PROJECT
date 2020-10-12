package jm.stockx;

import jm.stockx.dto.UserRegistrationDto;
import jm.stockx.entity.User;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

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
    public void registrationUser(@Valid UserRegistrationDto user) {

        String password = passwordGeneratorService.generatePassword(8);

        userService.createUser(new User(user.getFirstName(), user.getLastName(), user.getEmail(), password));

        mailService.sendSimpleMessage(
                user.getEmail(),
                "Registration success",
                "Thank you for your registration\n" +
                        "your password:" + password
        );
    }
}
