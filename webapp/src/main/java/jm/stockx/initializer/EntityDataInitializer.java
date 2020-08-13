package jm.stockx.initializer;

import jm.stockx.*;
import jm.stockx.entity.*;
import jm.stockx.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class EntityDataInitializer {

    private RoleService roleService;
    private UserService userService;
    private ItemService itemService;
    private BrandService brandService;
    private StyleService styleService;
    private NewsService newsService;
    private SellingInfoService sellingInfoService;
    private CurrencyService currencyService;
    private BidService bidService;

    @Autowired
    private void SetServices(RoleService roleService,
                             UserService userService,
                             ItemService itemService,
                             BrandService brandService,
                             StyleService styleService,
                             NewsService newsService,
                             SellingInfoService sellingInfoService,
                             CurrencyService currencyService,
                             BidService bidService) {
        this.userService = userService;
        this.itemService = itemService;
        this.roleService = roleService;
        this.brandService = brandService;
        this.styleService = styleService;
        this.newsService = newsService;
        this.sellingInfoService = sellingInfoService;
        this.currencyService = currencyService;
        this.bidService = bidService;
    }


    private void init() {
        log.info("Data init has been started!!!");

        fillDataBase();

        log.info("Data init has been done!!!");
    }

    private void fillDataBase() {
        createRoles();
        createUsers();              // DON'T WORKS with hibernate 6.0.0.Alpha5
        createBrands();
        createCurrency();
        createStyles();
        createItems();              // DON'T WORKS with hibernate 6.0.0.Alpha5
        createNews();
        //createSellingInfo();        // DON'T WORKS with hibernate 6.0.0.Alpha5
        //createBid();
    }

    private void createRoles() {
        if (roleService.getAll().size() == 0) {
            roleService.create(new Role("ROLE_ADMIN"));
            roleService.create(new Role("ROLE_USER"));
        }
    }

    private void createUsers() {
        if (userService.getAllUsers().size() == 0) {
            Admin admin = new Admin(
                    "Admin",
                    "God",
                    "admin.god@email.com",
                    "admin",
                    "admin",
                    (byte) 100,
                    true,
                    "ru",
                    "admin@apple.id");
            admin.setRole(roleService.getByRoleName("ROLE_ADMIN"));
            userService.createUser(admin);

            User user1 = new User(
                    "Vasya",
                    "Pupkin",
                    "vasya.pupkin@email.com",
                    "vasya.pupkin",
                    "1",
                    (byte) 10,
                    true,
                    "en",
                    "user1@apple.id");
            user1.setRole(roleService.getByRoleName("ROLE_USER"));
            userService.createUser(user1);

            User user2 = new User(
                    "Саша",
                    "Пушкин",
                    "sasha.pushkin@gmail.com",
                    "sasha.pushkin",
                    "1",
                    (byte) 13,
                    false,
                    "en",
                    "user2@apple.id");
            user2.setRole(roleService.getByRoleName("ROLE_USER"));
            userService.createUser(user2);
        }
    }

    private void createBrands() {
        if (brandService.getAll().size() == 0) {
            brandService.create(new Brand("Jordan"));
            brandService.create(new Brand("Adidas"));
            brandService.create(new Brand("Nike"));
        }
    }

    private void createCurrency() {
        if (currencyService.getAll().size() == 0) {
            currencyService.create(new Currency("USD"));
            currencyService.create(new Currency("EUR"));
            currencyService.create(new Currency("RUB"));
        }
    }

    private void createStyles() {
        if (styleService.getAll().size() == 0) {
            styleService.create(new Style("casual"));
            styleService.create(new Style("sports"));
            styleService.create(new Style("business"));
        }
    }

    private void createItems() {
        if (itemService.getAll().size() == 0) {

            itemService.create(new Item(
                    "Jordan 14 Retro Gym Red Toro",
                    190.0,
                    254.0,
                    316.0,
                    LocalDate.of(2020, 7, 2),
                    "New",
                    brandService.getBrandByName("Jordan"),
                    styleService.getStyleByName("sports")));

            itemService.create(new Item(
                    "Adidas Yeezy Boost 380 Mist",
                    230.0,
                    195.0,
                    230.0,
                    LocalDate.of(2020, 3, 25),
                    "New",
                    brandService.getBrandByName("Adidas"),
                    styleService.getStyleByName("sports")));

            itemService.create(new Item(
                    "Nike React Element 87 Anthracite Black",
                    160.0,
                    77.0,
                    101.0,
                    LocalDate.of(2018, 6, 14),
                    "New",
                    brandService.getBrandByName("Nike"),
                    styleService.getStyleByName("sports")));

            itemService.create(new Item(
                    "Jordan 4 Retro Winterized Loyal Blue",
                    200.0,
                    155.0,
                    212.0,
                    LocalDate.of(2019, 12, 21),
                    "New",
                    brandService.getBrandByName("Jordan"),
                    styleService.getStyleByName("sports")));

            itemService.create(new Item(
                    "Jordan 1 Retro High Satin Black Toe (W)",
                    160.0,
                    342.0,
                    442.0,
                    LocalDate.of(2019, 8, 17),
                    "New",
                    brandService.getBrandByName("Jordan"),
                    styleService.getStyleByName("sports")));
        }
    }

    private void createNews() {
        if (newsService.getAllNews().size() == 0) {

            newsService.create(new News(
                    "First",
                    LocalDateTime.of(2020, 8, 2, 7, 30),
                    "News title",
                    "Description",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                            "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                            "mollit anim id est laborum."));

            newsService.create(new News(
                    "ИМЯ НОВОСТИ - ЭТО ЧТО?",
                    LocalDateTime.of(2020, 8, 3, 2, 23),
                    "Заголовок новости - ясно",
                    "Описание новости - не очень ясно, но допустим",
                    "Вопрос: что такое name в сущности News? Может, удалить?"));
        }
    }

    private void createSellingInfo() {
        if (sellingInfoService.getAll().size() == 0) {

            sellingInfoService.create(new SellingInfo(
                    userService.getUserById(1L),
                    itemService.get(1L),
                    108L,
                    Status.DELIVERED));

            sellingInfoService.create(new SellingInfo(
                    userService.getUserById(2L),
                    itemService.get(2L),
                    109L,
                    Status.ACCEPTED));
        }
    }

    private void createBid() {
        if (bidService.getAll().size() == 0) {
            bidService.create(new Bid(
                    200.0,
                    false,
                    userService.getUserById(2L),
                    itemService.get(3L)));
        }
    }

}
