package jm.stockx.initializer;


import jm.stockx.*;
import jm.stockx.initializer.initmodels.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Класс-иницализатор записей базы данных.
 * Заполняет указанными сущностями создаваемые ORM таблицы.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EntityDataInitializer implements CommandLineRunner {
    private final RoleInit roleInit;
    private final UserInit userInit;
    private final CurrencyInit currencyInit;
    private final StyleInit styleInit;
    private final BrandInit brandInit;
    private final ItemSizeInit itemSizeInit;
    private final ItemInit itemInit;
    private final ItemInfoInit itemInfoInit;
    private final SellingInfoInit sellingInfoInit;
    private final NewsInit newsInit;
    private final BidInit bidInit;
    private final UserLocaliseInfoInit userLocaliseInfoInit;

    private final RoleService roleService;
    private final UserService userService;
    private final CurrencyService currencyService;
    private final StyleService styleService;
    private final BrandService brandService;
    private final ItemSizeService itemSizeService;
    private final ItemService itemService;
    private final ItemInfoService itemInfoService;
    private final SellingInfoService sellingInfoService;
    private final BidService bidService;
    private final NewsService newsService;
    private final UserLocaliseInfoService userLocaliseInfoService;

    @Override
    public void run(String... args) {
        log.info("Data init has been started!!!");

        fillDataBase();

        log.info("Data init has been done!!!");
    }

    /**
     * При изменении:
     * - порядка вызовов метдов
     * - содержимого классов инициализаторов
     * работоспособность не гарантируется.
     * Изменять в крайнем случае.
     */
    private void fillDataBase() {
        createRoles();
        createUsers();
        createCurrency();
        createStyles();
        createBrands();
        createShoeSizes();
        createItems();
        createItemInfo();
        createSellingInfo();
        createBids();
        createNews();
        createUserLocaliseInfos();
    }

    private void createRoles() {
        if (roleService.getAll().isEmpty()) {
            roleInit.initializeRoles();
        }
    }

    private void createUsers() {
        if (userService.getAllUsers().isEmpty()) {
            userInit.initializeUsers();
            userInit.initializeNotification();
        }
    }

    private void createCurrency() {
        if (currencyService.getAll().isEmpty()) {
            currencyInit.initializeCurrencies();
        }
    }

    private void createStyles() {
        if (styleService.getAll().isEmpty()) {
            styleInit.initializeStyles();
        }
    }

    private void createBrands() {
        if (brandService.getAll().isEmpty()) {
            brandInit.initializeBrands();
        }
    }

    private void createShoeSizes() {
        if (itemSizeService.getAll().isEmpty()) {
            itemSizeInit.initializeItemSizes();
        }
    }

    private void createItems() {
        if (itemService.getAll().isEmpty()) {
            itemInit.initializeItems();
        }
    }

    private void createItemInfo() {
        if (itemInfoService.getAll().isEmpty()) {
            itemInfoInit.initializeItemInfos();
        }
    }

    private void createSellingInfo() {
        if (sellingInfoService.getAll().isEmpty()) {
            sellingInfoInit.initializeSellingInfos();
        }
    }

    private void createBids() {
        if (bidService.getAll().isEmpty()) {
            bidInit.initializeBids();
        }
    }

    private void createNews() {
        if (newsService.getAllNews().isEmpty()) {
            newsInit.initializeNews();
        }
    }

    private void createUserLocaliseInfos() {
        if (userLocaliseInfoService.getAll().isEmpty()) {
            userLocaliseInfoInit.initializeUserLocaliseInfos();
        }
    }
}