package jm.stockx.initializer;

import jm.stockx.*;
import jm.stockx.api.dao.ItemPriceChartDAO;
import jm.stockx.entity.*;
import jm.stockx.enums.ItemCategory;
import jm.stockx.enums.ItemColors;
import jm.stockx.enums.ItemSizeTypes;
import jm.stockx.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class EntityDataInitializer {

    @Autowired
    ItemPriceChartDAO dao;
    private RoleService             roleService;
    private UserService             userService;
    private ItemService             itemService;
    private BrandService            brandService;
    private StyleService            styleService;
    private NewsService             newsService;
    private CurrencyService         currencyService;
    private ItemInfoService         itemInfoService;
    private ItemSizeService         itemSizeService;
    private NotificationInfoService notificationInfoService;

    @Autowired
    private void setServices(RoleService roleService,
                             UserService userService,
                             ItemService itemService,
                             BrandService brandService,
                             StyleService styleService,
                             NewsService newsService,
                             CurrencyService currencyService,
                             ItemInfoService itemInfoService,
                             ItemSizeService itemSizeService, NotificationInfoService notificationInfoService) {
        this.userService = userService;
        this.itemService = itemService;
        this.roleService = roleService;
        this.brandService = brandService;
        this.styleService = styleService;
        this.newsService = newsService;
        this.currencyService = currencyService;
        this.itemInfoService = itemInfoService;
        this.itemSizeService = itemSizeService;
        this.notificationInfoService = notificationInfoService;
    }


    private void init() {
        log.info("Data init has been started!!!");

        fillDataBase();

        log.info("Data init has been done!!!");
    }

    private void fillDataBase() {
        createShoeSizes();
        createRoles();
        createUsers();
        createBrands();
        createCurrency();
        createStyles();
        createNews();
        createItems();
        printSales();

    }

    void printSales() {
       dao.get12LatestSales(1L);
    }

    private void createRoles() {
        if (roleService.getAll().isEmpty()) {
            roleService.create(new Role("ROLE_ADMIN"));
            roleService.create(new Role("ROLE_USER"));
        }
    }

    private void createUsers() {
        if (userService.getAllUsers().isEmpty()) {
            Admin admin = new Admin(
                    "Thor",
                    "Odinson",
                    "admin@mail.ru",
                    "admin",
                    "admin",
                    (byte) 100,
                    true,
                    "ru",
                    "admin@apple.id");
            admin.setActive(true);
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

            NotificationInfo notificationInfo = new NotificationInfo();
            notificationInfo.setUser(user2);
            notificationInfoService.createNotificationInfo(notificationInfo);
        }
    }

    private void createBrands() {
        if (brandService.getAll().isEmpty()) {
            brandService.create(new Brand("Jordan"));
            brandService.create(new Brand("Adidas"));
            brandService.create(new Brand("Nike"));
            brandService.create(new Brand("New Balance"));
            brandService.create(new Brand("Saucony"));
        }
    }

    private void createCurrency() {
        if (currencyService.getAll().isEmpty()) {
            currencyService.create(new Currency("USD"));
            currencyService.create(new Currency("EUR"));
            currencyService.create(new Currency("RUB"));
        }
    }

    private void createStyles() {
        if (styleService.getAll().isEmpty()) {
            styleService.create(new Style("casual"));
            styleService.create(new Style("sports"));
            styleService.create(new Style("business"));
        }
    }

    private void createItems() {
        if (itemService.getAll().isEmpty()) {

            Item jordan14 = new SneakersItem(("Jordan 14 Retro Gym Red Toro"));

            itemService.create(jordan14);


            BuyingInfo buyingInfoJordan14 = BuyingInfo.builder()
                    .buyingPrice(Money.parse("RUB 205.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            BuyingInfo buyingInfoJordan15 = BuyingInfo.builder()
                    .buyingPrice(Money.parse("RUB 206.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            BuyingInfo buyingInfoJordan16 = BuyingInfo.builder()
                    .buyingPrice(Money.parse("RUB 207.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(jordan14)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("RUB 200.0"))
                    .highestBid(Money.parse("RUB 222.0"))
                    .lowestAsk(Money.parse("RUB 191.0"))
                    .buyingInfo(buyingInfoJordan15)
                    .size(itemSizeService.findOneBySizeName("9"))
                    .releaseDate(LocalDate.of(2020, 7, 2))
                    .condition("New")
                    .description("Jordan Brand released a new Chicago Bulls themed colorway with the Jordan 14 Retro Gym Red Toro, now available on StockX. " +
                            "This release draws a close resemblance to the Jordan 14 Challenge Red, but instead of yellow detailing to mimic it’s " +
                            "Ferrari inspiration, the Gym Red 14 implements white panels to create the Bulls uniform vibe.\n" +
                            "\n" +
                            "The Jordan 14 Gym Red Toro features a red suede upper atop a black and white sole. A black woven tongue, " +
                            "tire-like rubber heel tab, and arch underlay complete the design. These Jordan 14s released in July of 2020 and " +
                            "retailed for $190 USD.")
                    .brand(brandService.getBrandByName("Jordan"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.BLACK)
                    .build());

            itemInfoService.create(ItemInfo.builder()
                    .item(jordan14)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("RUB 200.0"))
                    .highestBid(Money.parse("RUB 222.0"))
                    .lowestAsk(Money.parse("RUB 191.0"))
                    .buyingInfo(buyingInfoJordan16)
                    .size(itemSizeService.findOneBySizeName("8"))
                    .releaseDate(LocalDate.of(2020, 7, 2))
                    .condition("New")
                    .description("Jordan Brand released a new Chicago Bulls themed colorway with the Jordan 14 Retro Gym Red Toro, now available on StockX. " +
                            "This release draws a close resemblance to the Jordan 14 Challenge Red, but instead of yellow detailing to mimic it’s " +
                            "Ferrari inspiration, the Gym Red 14 implements white panels to create the Bulls uniform vibe.\n" +
                            "\n" +
                            "The Jordan 14 Gym Red Toro features a red suede upper atop a black and white sole. A black woven tongue, " +
                            "tire-like rubber heel tab, and arch underlay complete the design. These Jordan 14s released in July of 2020 and " +
                            "retailed for $190 USD.")
                    .brand(brandService.getBrandByName("Jordan"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.BLACK)
                    .build());

            itemInfoService.create(ItemInfo.builder()
                    .item(jordan14)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("RUB 200.0"))
                    .highestBid(Money.parse("RUB 222.0"))
                    .lowestAsk(Money.parse("RUB 191.0"))
                    .buyingInfo(buyingInfoJordan14)
                    .size(itemSizeService.findOneBySizeName("7"))
                    .releaseDate(LocalDate.of(2020, 7, 2))
                    .condition("New")
                    .description("Jordan Brand released a new Chicago Bulls themed colorway with the Jordan 14 Retro Gym Red Toro, now available on StockX. " +
                            "This release draws a close resemblance to the Jordan 14 Challenge Red, but instead of yellow detailing to mimic it’s " +
                            "Ferrari inspiration, the Gym Red 14 implements white panels to create the Bulls uniform vibe.\n" +
                            "\n" +
                            "The Jordan 14 Gym Red Toro features a red suede upper atop a black and white sole. A black woven tongue, " +
                            "tire-like rubber heel tab, and arch underlay complete the design. These Jordan 14s released in July of 2020 and " +
                            "retailed for $190 USD.")
                    .brand(brandService.getBrandByName("Jordan"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.BLACK)
                    .build());


            Item adidasYeezy = new SneakersItem(("Adidas Yeezy Boost 380 Mist"));
            itemService.create(adidasYeezy);

            BuyingInfo buyingInfoAdidasYeezy = BuyingInfo.builder()
                    .buyingPrice(Money.parse("USD 250.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(adidasYeezy)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("USD 240.0"))
                    .highestBid(Money.parse("USD 272.0"))
                    .lowestAsk(Money.parse("USD 204.0"))
                    .buyingInfo(buyingInfoAdidasYeezy)
                    .size(itemSizeService.findOneBySizeName("9"))
                    .releaseDate(LocalDate.of(2020, 3, 25))
                    .condition("New")
                    .description("Yeezy added a new colorway to their Boost 380 product line with the adidas Yeezy Boost 380 Mist, " +
                            "now available on StockX. This model was originally known to be the Yeezy Boost 350 V3, but upon release " +
                            "it was given its own silhouette name. The mist colorway released in both reflective and non-reflective " +
                            "variations.\n" +
                            "\n" +
                            "This 380 Mist] features a Mist Primeknit pattern on its upper and lacks the traditional " +
                            "lateral side stripe. An upgraded translucent Boost midsole and engineered gum outsole grip complete " +
                            "the design. These sneakers released in March of 2020 and retailed for $230.")
                    .brand(brandService.getBrandByName("Adidas"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.BLACK)
                    .build());


            Item nikeReact = new SneakersItem(("Nike React Element 87 Anthracite Black"));
            itemService.create(nikeReact);

            BuyingInfo buyingInfoNikeReact = BuyingInfo.builder()
                    .buyingPrice(Money.parse("USD 200.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(nikeReact)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("USD 190.0"))
                    .highestBid(Money.parse("USD 213.0"))
                    .lowestAsk(Money.parse("USD 166.0"))
                    .buyingInfo(buyingInfoNikeReact)
                    .size(itemSizeService.findOneBySizeName("10"))

                    .releaseDate(LocalDate.of(2018, 6, 14))
                    .condition("New")
                    .description("Since first being spotted on the runway during a Paris Fashion Week Show in March, " +
                            "the Nike React Element 87 has been of the most hyped shoes of 2018. Mimicking a Virgil Abloh-esque " +
                            "deconstructed style the react Element 87 features a transcluscent upper and a React-cushioned midsole. " +
                            "Released exclusively overseas in June, this pair saw an American release in July 2018 at a retail " +
                            "price of $160.")
                    .brand(brandService.getBrandByName("Nike"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.WHITE)
                    .build());

            Item jordan4 = new SneakersItem(("Jordan 4 Retro Winterized Loyal Blue"));
            itemService.create(jordan4);

            BuyingInfo buyingInfoJordan4 = BuyingInfo.builder()
                    .buyingPrice(Money.parse("USD 220.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(jordan4)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("USD 210.0"))
                    .highestBid(Money.parse("USD 237.0"))
                    .lowestAsk(Money.parse("USD 195.0"))
                    .buyingInfo(buyingInfoJordan4)
                    .size(itemSizeService.findOneBySizeName("10"))

                    .releaseDate(LocalDate.of(2019, 12, 21))
                    .condition("New")
                    .description("Jordan Brand spins an iconic design for winter with the Jordan 4 Retro Winterized Loyal Blue, " +
                            "now available on StockX. Reminiscent of the extremely limited Eminem Encore 4, this release gives " +
                            "many of us the opportunity to own a blue toned Jordan 4 without having to spend more than the cost of a car. " +
                            "The difference between this winterized design and a traditional Jordan 4 lies in the material choices. " +
                            "The Winterized 4 replaces the classic mesh insert panels with a canvas-like material and adopts a fleece lining " +
                            "to retain warmth.")
                    .brand(brandService.getBrandByName("Jordan"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.WHITE)
                    .build());


            Item jordan1 = new SneakersItem(("Jordan 1 Retro High Satin Black Toe (W)"));
            itemService.create(jordan1);

            BuyingInfo buyingInfoJordan1 = BuyingInfo.builder()
                    .buyingPrice(Money.parse("USD 214.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(jordan1)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("USD 200.0"))
                    .highestBid(Money.parse("USD 241.0"))
                    .lowestAsk(Money.parse("USD 167.0"))
                    .buyingInfo(buyingInfoJordan1)
                    .size(itemSizeService.findOneBySizeName("11"))
                    .releaseDate(LocalDate.of(2019, 8, 17))
                    .condition("New")
                    .description("Jordan Brand adds a twist to a classic with the Air Jordan 1 WMNS Satin “Black Toe”, now available on StockX. " +
                            "Jordan is no stranger to adding satin to the Jordan 1. In May of 2018, they released a satin rendition of the " +
                            "“Shattered Backboard” 1 in a similar fashion, revealing that the release would be in women’s sizing.\n" +
                            "\n" +
                            "This AJ 1 features classic “Black Toe” color scheme. This design is constructed in a mix of leather and satin " +
                            "construction providing a luxury feel. A metal Wings logo on the heel completes the design. These sneakers released " +
                            "in August of 2019 and retailed for $160.")
                    .brand(brandService.getBrandByName("Jordan"))
                    .style(styleService.getStyleByName("sports"))
                    .itemColors(ItemColors.BROWN)
                    .build());

            Item newBalance990v3JJJJound = new SneakersItem(("New Balance 990v3 JJJJound"));
            itemService.create(newBalance990v3JJJJound);

            BuyingInfo buyingInfoNewBalance990v3JJJJound = BuyingInfo.builder()
                    .buyingPrice(Money.parse("USD 3501.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(newBalance990v3JJJJound)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("USD 1500.0"))
                    .highestBid(Money.parse("USD 1787.0"))
                    .lowestAsk(Money.parse("USD 9654.0"))
                    .buyingInfo(buyingInfoNewBalance990v3JJJJound)
                    .size(itemSizeService.findOneBySizeName("11"))
                    .releaseDate(LocalDate.of(2018, 12, 3))
                    .condition("New")
                    .description("The neutral colorway is in keeping with Justin Saunders' signature aesthetic, " +
                            "and is sure to be attractive to those with more toned-down sensibilities.")
                    .brand(brandService.getBrandByName("New Balance"))
                    .style(styleService.getStyleByName("casual"))
                    .itemColors(ItemColors.GRAY)
                    .build());

            Item sauconyAzuraBodegaLucky13 = new SneakersItem(("Saucony Azura Bodega Lucky 13"));
            itemService.create(sauconyAzuraBodegaLucky13);

            BuyingInfo buyingInfoSauconyAzuraBodegaLucky13 = BuyingInfo.builder()
                    .buyingPrice(Money.parse("USD 801.0"))
                    .status(Status.DELIVERED)
                    .buyingTimeStamp(LocalDateTime.now())
                    .build();

            itemInfoService.create(ItemInfo.builder()
                    .item(sauconyAzuraBodegaLucky13)
                    .itemCategory(ItemCategory.SNEAKERS)
                    .price(Money.parse("USD 800.0"))
                    .highestBid(Money.parse("USD 166.0"))
                    .lowestAsk(Money.parse("USD 800.0"))
                    .buyingInfo(buyingInfoSauconyAzuraBodegaLucky13)
                    .size(itemSizeService.findOneBySizeName("12"))
                    .releaseDate(LocalDate.of(2222, 5, 16))
                    .condition("New")
                    .description("The sneaker is predominantly black with dark pops of color throughout, " +
                            "including red on the medial Saucony branding and heel, purple on the tongue, " +
                            "and a multicolored outsole. " +
                            "Reflective detailing is present on the toe box, while the midsole is speckled.")
                    .brand(brandService.getBrandByName("Saucony"))
                    .style(styleService.getStyleByName("casual"))
                    .itemColors(ItemColors.BLACK)
                    .build());
        }
    }

    private void createNews() {
        if (newsService.getAllNews().isEmpty()) {

            newsService.create(new News(
                    "News#1 title",
                    LocalDateTime.of(2020, 8, 2, 7, 30),
                    "News#1 title",
                    "Description",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                            "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                            "mollit anim id est laborum."));

            newsService.create(new News(
                    "News#2 title",
                    LocalDateTime.of(2020, 8, 4, 2, 23),
                    "News#2 title",
                    "Описание новости - не очень ясно, но допустим",
                    "Вопрос: что такое name в сущности News? Может, удалить?"));

            newsService.create(new News(
                    "News#3 title",
                    LocalDateTime.of(2020, 8, 5, 2, 23),
                    "News#3 title",
                    "Описание новости - не очень ясно, но допустим",
                    "Вопрос: что такое name в сущности News? Может, удалить?"));

            newsService.create(new News(
                    "News#4 title",
                    LocalDateTime.of(2020, 8, 6, 2, 23),
                    "News#4 title",
                    "Описание новости - не очень ясно, но допустим",
                    "Вопрос: что такое name в сущности News? Может, удалить?"));

            newsService.create(new News(
                    "News#5 title",
                    LocalDateTime.of(2020, 8, 7, 2, 23),
                    "News#5 title",
                    "Описание новости - не очень ясно, но допустим",
                    "Вопрос: что такое name в сущности News? Может, удалить?"));

            newsService.create(new News(
                    "News#6 title",
                    LocalDateTime.of(2020, 8, 8, 2, 23),
                    "News#6 title",
                    "Описание новости - не очень ясно, но допустим",
                    "Вопрос: что такое name в сущности News? Может, удалить?"));
        }
    }

    private void createShoeSizes() {
        if (itemSizeService.getAll().isEmpty()) {
            itemSizeService.create(new ItemSize("7", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("7.5", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("8", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("8.5", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("9", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("9.5", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("10", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("10.5", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("11", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("11.5", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("12", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("12.5", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("13", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("14", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("15", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("16", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("17", ItemSizeTypes.MEN));
            itemSizeService.create(new ItemSize("18", ItemSizeTypes.MEN));
        }
    }

}
