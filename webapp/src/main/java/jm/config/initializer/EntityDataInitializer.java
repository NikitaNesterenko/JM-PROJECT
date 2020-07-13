package jm.config.initializer;

import jm.Item;
import jm.User;
import jm.api.dao.ItemDAO;
import jm.api.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntityDataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(EntityDataInitializer.class);

    private final UserDAO userDao;

    private final ItemDAO itemDao;

    @Autowired
    public EntityDataInitializer(UserDAO userDao, ItemDAO itemDao) {
        this.userDao = userDao;
        this.itemDao = itemDao;

        init();
    }

    private void init() {
        logger.info("Data init has been started!!!");

        saveEntity();

        logger.info("Data init has been done!!!");
    }

    private void saveEntity() {
        createUsers().forEach(userDao::add);
        createItems().forEach(itemDao::add);
    }

    private List<User> createUsers() {

        List<User> userList = new ArrayList<>();

        userList.add(new User("Vasya", "Pupkin", "vasya.pupkin@email.com",
                "vasya.pupkin", "1", (byte) 10, true));

        userList.add(new User("Petya", "Vakulov", "petya.vakulov@email.com",
                "petya.vakulov", "1", (byte) 12, true));

        userList.add(new User("Semen","Semenych","semen.semenych@email.com",
                "semen.semenych","1",(byte) 22,true));

        return userList;
    }

    private List<Item> createItems() {
        List<Item> itemList = new ArrayList<>();

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
