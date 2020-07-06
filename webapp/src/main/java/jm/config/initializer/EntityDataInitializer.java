package jm.config.initializer;

import jm.User;
import jm.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityDataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(EntityDataInitializer.class);

    @Autowired
    private UserService userService;

    @Autowired
    public EntityDataInitializer(UserService userService) {
        this.userService = userService;
        init();
    }

    private void init() {
        logger.info("Data init has been started!!!");
        dataInit();
        logger.info("Data init has been done!!!");
    }

    private void dataInit() {
        createUsers();
    }

    private void createUsers() {

        User userVasya = new User();

        userVasya.setEmail("vasya.pupkin@email.com");
        userVasya.setUsername("vasya.pupkin");
        userVasya.setPassword("1");
        userVasya.setFirstName("Vasya");
        userVasya.setLastName("Pupkin");
        userVasya.setSellerLevel((byte) 10);
        userVasya.setVacationMode(true);

        userService.createUser(userVasya);


        User userPetya = new User();

        userPetya.setEmail("petya.vakulov@email.com");
        userPetya.setUsername("petya.vakulov");
        userPetya.setPassword("1");
        userPetya.setFirstName("Petya");
        userPetya.setLastName("Vakulov");
        userPetya.setSellerLevel((byte) 12);
        userPetya.setVacationMode(true);

        userService.createUser(userPetya);


        User userSemen = new User();

        userSemen.setEmail("semen.semenych@email.com");
        userSemen.setUsername("semen.semenych");
        userSemen.setPassword("1");
        userSemen.setFirstName("Semen");
        userSemen.setLastName("Semenych");
        userSemen.setSellerLevel((byte) 22);
        userSemen.setVacationMode(true);

        userService.createUser(userSemen);
    }

}
