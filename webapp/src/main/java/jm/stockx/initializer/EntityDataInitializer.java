package jm.stockx.initializer;

import jm.stockx.BidService;
import jm.stockx.BrandService;
import jm.stockx.CurrencyService;
import jm.stockx.ItemInfoService;
import jm.stockx.ItemService;
import jm.stockx.NewsService;
import jm.stockx.RoleService;
import jm.stockx.SellingInfoService;
import jm.stockx.StyleService;
import jm.stockx.UserService;
import jm.stockx.entity.Admin;
import jm.stockx.entity.Bid;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Currency;
import jm.stockx.entity.Item;
import jm.stockx.entity.News;
import jm.stockx.entity.Role;
import jm.stockx.entity.SellingInfo;
import jm.stockx.entity.Style;
import jm.stockx.entity.User;
import jm.stockx.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
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
    private ItemInfoService itemInfoService;

    @Autowired
    private void SetServices(RoleService roleService,
                             UserService userService,
                             ItemService itemService,
                             BrandService brandService,
                             StyleService styleService,
                             NewsService newsService,
                             SellingInfoService sellingInfoService,
                             CurrencyService currencyService,
                             BidService bidService,
                             ItemInfoService itemInfoService) {
        this.userService = userService;
        this.itemService = itemService;
        this.roleService = roleService;
        this.brandService = brandService;
        this.styleService = styleService;
        this.newsService = newsService;
        this.sellingInfoService = sellingInfoService;
        this.currencyService = currencyService;
        this.bidService = bidService;
        this.itemInfoService = itemInfoService;
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
            admin.setRole(roleService.getRole("ROLE_ADMIN"));
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
            user1.setRole(roleService.getRole("ROLE_USER"));
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
            user2.setRole(roleService.getRole("ROLE_USER"));
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
                    Money.parse("RUB 200.0"),
                    LocalDate.of(2020, 7, 2),
                    "New",
                    "Jordan Brand released a new Chicago Bulls themed colorway with the Jordan 14 Retro Gym Red Toro, now available on StockX. " +
                            "This release draws a close resemblance to the Jordan 14 Challenge Red, but instead of yellow detailing to mimic it’s " +
                            "Ferrari inspiration, the Gym Red 14 implements white panels to create the Bulls uniform vibe.\n" +
                            "\n" +
                            "The Jordan 14 Gym Red Toro features a red suede upper atop a black and white sole. A black woven tongue, " +
                            "tire-like rubber heel tab, and arch underlay complete the design. These Jordan 14s released in July of 2020 and " +
                            "retailed for $190 USD.",
                    brandService.getBrand("Jordan"),
                    styleService.getStyle("sports")));

            itemService.create(new Item(
                    "Adidas Yeezy Boost 380 Mist",
                    Money.parse("USD 240.0"),
                    LocalDate.of(2020, 3, 25),
                    "New",
                    "Yeezy added a new colorway to their Boost 380 product line with the adidas Yeezy Boost 380 Mist, " +
                            "now available on StockX. This model was originally known to be the Yeezy Boost 350 V3, but upon release " +
                            "it was given its own silhouette name. The mist colorway released in both reflective and non-reflective " +
                            "variations.\n" +
                            "\n" +
                            "This 380 Mist] features a Mist Primeknit pattern on its upper and lacks the traditional " +
                            "lateral side stripe. An upgraded translucent Boost midsole and engineered gum outsole grip complete " +
                            "the design. These sneakers released in March of 2020 and retailed for $230.",
                    brandService.getBrand("Adidas"),
                    styleService.getStyle("sports")));

            itemService.create(new Item(
                    "Nike React Element 87 Anthracite Black",
                    Money.parse("USD 190.0"),
                    LocalDate.of(2018, 6, 14),
                    "New",
                    "Since first being spotted on the runway during a Paris Fashion Week Show in March, " +
                            "the Nike React Element 87 has been of the most hyped shoes of 2018. Mimicking a Virgil Abloh-esque " +
                            "deconstructed style the react Element 87 features a transcluscent upper and a React-cushioned midsole. " +
                            "Released exclusively overseas in June, this pair saw an American release in July 2018 at a retail " +
                            "price of $160.",
                    brandService.getBrand("Nike"),
                    styleService.getStyle("sports")));

            itemService.create(new Item(
                    "Jordan 4 Retro Winterized Loyal Blue",
                    Money.parse("USD 210.0"),
                    LocalDate.of(2019, 12, 21),
                    "New",
                    "Jordan Brand spins an iconic design for winter with the Jordan 4 Retro Winterized Loyal Blue, " +
                            "now available on StockX. Reminiscent of the extremely limited Eminem Encore 4, this release gives " +
                            "many of us the opportunity to own a blue toned Jordan 4 without having to spend more than the cost of a car. " +
                            "The difference between this winterized design and a traditional Jordan 4 lies in the material choices. " +
                            "The Winterized 4 replaces the classic mesh insert panels with a canvas-like material and adopts a fleece lining " +
                            "to retain warmth.",
                    brandService.getBrand("Jordan"),
                    styleService.getStyle("sports")));

            itemService.create(new Item(
                    "Jordan 1 Retro High Satin Black Toe (W)",
                    Money.parse("USD 200.0"),
                    LocalDate.of(2019, 8, 17),
                    "New",
                    "Jordan Brand adds a twist to a classic with the Air Jordan 1 WMNS Satin “Black Toe”, now available on StockX. " +
                            "Jordan is no stranger to adding satin to the Jordan 1. In May of 2018, they released a satin rendition of the " +
                            "“Shattered Backboard” 1 in a similar fashion, revealing that the release would be in women’s sizing.\n" +
                            "\n" +
                            "This AJ 1 features classic “Black Toe” color scheme. This design is constructed in a mix of leather and satin " +
                            "construction providing a luxury feel. A metal Wings logo on the heel completes the design. These sneakers released " +
                            "in August of 2019 and retailed for $160.",
                    brandService.getBrand("Jordan"),
                    styleService.getStyle("sports")));
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
                    itemService.getItemById(1L),
                    itemInfoService.getItemInfoByItemId(1L),
                    108L,
                    Status.DELIVERED));

            sellingInfoService.create(new SellingInfo(
                    userService.getUserById(2L),
                    itemService.getItemById(2L),
                    itemInfoService.getItemInfoByItemId(2L),
                    109L,
                    Status.ACCEPTED));
        }
    }

    private void createBid() {
        if (bidService.getAll().size() == 0) {
            bidService.create(new Bid(
                    Money.parse("USD 200.0"),
                    false,
                    userService.getUserById(2L),
                    itemService.getItemById(3L)));
        }
    }
}
