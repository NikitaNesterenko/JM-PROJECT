package jm.stockx.initializer;

import jm.stockx.ItemService;
import jm.stockx.RoleService;
import jm.stockx.UserService;
import jm.stockx.entity.Admin;
import jm.stockx.entity.Item;
import jm.stockx.entity.Role;
import jm.stockx.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityDataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(EntityDataInitializer.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private RoleService roleService;

    private List<Role> roleList = new ArrayList<>();

    private List<User> userList = new ArrayList<>();

    private List<Item> itemList = new ArrayList<>();

    private void init() {
        logger.info("Data init has been started!!!");

        saveEntity();

        logger.info("Data init has been done!!!");
    }

    private void saveEntity() {
        createRoles().forEach(roleService::create);
        roleList = roleService.getAll();
        createUsers().forEach(userService::createUser);
        createItems().forEach(itemService::create);
    }

    private List<Role> createRoles() {
        roleList.add(new Role("ROLE_ADMIN"));
        roleList.add(new Role("ROLE_USER"));

        return roleList;
    }

    private List<User> createUsers() {

        Admin admin = new Admin("Admin", "God", "admin.god@email.com",
                "admin", "admin", (byte) 100, true);
        admin.setRole(roleList.get(0));
        userList.add(admin);

        User user1 = new User("Vasya", "Pupkin", "vasya.pupkin@email.com",
                "vasya.pupkin", "1", (byte) 10, true);
        user1.setRole(roleList.get(1));
        userList.add(user1);

        User user2 = new User("Petya", "Vakulov", "petya.vakulov@email.com",
                "petya.vakulov", "1", (byte) 12, true);
        user2.setRole(roleList.get(1));
        userList.add(user2);

        User user3 = new User("Semen","Semenych","semen.semenych@email.com",
                "semen.semenych","1",(byte) 22,true);
        user3.setRole(roleList.get(1));
        userList.add(user3);

        return userList;
    }

    private List<Item> createItems() {

        itemList.add(new Item("Jordan 14 Retro Gym Red Toro",190.0,254.0,316.0,
                LocalDate.of(2020, 7, 2),"New"));
        // size 12

        itemList.add(new Item("Adidas Yeezy Boost 380 Mist", 230.0, 195.0, 230.0,
                LocalDate.of(2020, 3, 25), "New"));
        //size 8.5

        itemList.add(new Item("Nike React Element 87 Anthracite Black", 160.0, 77.0, 101.0,
                LocalDate.of(2018, 6, 14), "New"));
        //size 9

        itemList.add(new Item("Jordan 4 Retro Winterized Loyal Blue", 200.0, 155.0, 212.0,
                LocalDate.of(2019, 12, 21), "New"));
        //size 7.5

        itemList.add(new Item("Jordan 1 Retro High Satin Black Toe (W)", 160.0, 342.0, 442.0,
                LocalDate.of(2019, 8, 17), "New"));
        //size 11W

        return itemList;
    }

}
