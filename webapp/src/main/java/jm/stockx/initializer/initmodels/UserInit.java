package jm.stockx.initializer.initmodels;

import jm.stockx.NotificationInfoService;
import jm.stockx.RoleService;
import jm.stockx.UserService;
import jm.stockx.entity.Admin;
import jm.stockx.entity.NotificationInfo;
import jm.stockx.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс для создания
 * - пользователей
 * - NotificationInfo
 * в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class UserInit {
    public static final String ROLE_USER = "ROLE_USER";
    private final RoleService roleService;
    private final UserService userService;
    private final NotificationInfoService notificationInfoService;

    public void initializeUsers() {
        List<User> userListForCreation = createUsersForInitialization();
        userListForCreation.forEach(userService::createUser);
    }

    public void initializeNotification() {
        NotificationInfo notificationInfo = new NotificationInfo();
        notificationInfo.setUser(userService.getUserById(4L));
        notificationInfoService.createNotificationInfo(notificationInfo);
    }

    private List<User> createUsersForInitialization() {
        Admin admin = new Admin(
                "John",
                "Galt",
                "admin@mail.ru",
                "admin",
                "admin",
                (byte) 100,
                false,
                "ru",
                "admin@apple.id");
        admin.setActive(true);
        admin.setRole(roleService.getRole("ROLE_ADMIN"));

        User userPupkin = User.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .email("vasya.pupkin@email.com")
                .username("vasyahero")
                .password("pass")
                .sellerLevel((byte) 10)
                .vacationMode(true)
                .appleUserId("en")
                .localeTag("user1@apple.id")
                .role(roleService.getRole(ROLE_USER))
                .active(false)
                .build();

        User userPushkin = User.builder()
                .firstName("Саша")
                .lastName("Пушкин")
                .email("sasha.pushkin@gmail.com")
                .username("alexpistoletov")
                .password("pass")
                .sellerLevel((byte) 37)
                .vacationMode(false)
                .appleUserId("en")
                .localeTag("user2@apple.id")
                .role(roleService.getRole(ROLE_USER))
                .active(true)
                .build();

        User userRyan = User.builder()
                .firstName("Andrew")
                .lastName("Ryan")
                .email("andrew.ryan@rapture.us")
                .username("golflover")
                .password("pass")
                .sellerLevel((byte) 99)
                .vacationMode(false)
                .appleUserId("en")
                .localeTag("ryan@apple.id")
                .role(roleService.getRole(ROLE_USER))
                .active(true)
                .build();

        return List.of(admin, userPupkin, userPushkin, userRyan);
    }
}