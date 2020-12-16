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

    private final RoleService roleService;

    public UserRegistrationServiceImpl(UserService userService, MailService mailService, PasswordGeneratorServiceImpl passwordGeneratorService, RoleService roleService) {
        this.userService = userService;
        this.mailService = mailService;
        this.passwordGeneratorService = passwordGeneratorService;
        this.roleService = roleService;
    }

    @Override
    public void registrationUser(@Valid UserRegistrationDto user) throws UserExistsException, UserNotFoundException {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            throw new UserExistsException();
        }

        String password = passwordGeneratorService.generatePassword(8);

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), password);
        newUser.setRole(roleService.getRole("ROLE_USER"));
        newUser.setActive(true);

        userService.createUser(newUser);

        mailService.sendSimpleMessage(
                user.getEmail(),
                "Registration success",
                "Thank you for your registration\n" +
                        "your password:" + password
        );
    }
}
