package jm.stockx.initializer.initmodels;

import jm.stockx.*;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import jm.stockx.enums.ItemColors;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс для создания дополнительной информации о вещи в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class ItemInfoInit {
    public static final String SPORTS = "sports";
    public static final String CASUAL = "casual";
    public static final String JORDAN = "Jordan";

    private final StyleService styleService;
    private final BrandService brandService;
    private final BuyingInfoInit buyingInfoInit;
    private final ItemSizeService itemSizeService;
    private final ItemService itemService;
    private final ItemInfoService itemInfoService;

    public void initializeItemInfos() {
        Map<String, ItemInfo> itemsForCreation = createItemsForInitialization();
        itemsForCreation.values().forEach(itemInfoService::create);
    }

    private Map<String, ItemInfo> createItemsForInitialization() {
        // Adidas
        ItemInfo adidasYeezyBoost380MistII = ItemInfo.builder()
                .item(itemService.getItemByName("Adidas Yeezy Boost 380 Mist"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 240.0"))
                .highestBid(Money.parse("USD 272.0"))
                .lowestAsk(Money.parse("USD 204.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfoAdidasYeezyBoost380MistBI"))
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
                .style(styleService.getStyleByName(SPORTS))
                .itemColors(ItemColors.BLACK)
                .build();

        // Converse
        ItemInfo converseAllStar70sHiKithxCocaColaII = ItemInfo.builder()
                .item(itemService.getItemByName("Converse Chuck Taylor All Star 70s Hi Kith x Coca Cola Golden Rod/Dress Blues (Friends and Family)"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 1650.0"))
                .highestBid(Money.parse("USD 500.0"))
                .lowestAsk(Money.parse("USD 1402.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("converseAllStar70sHiKithxCocaColaBI1"))
                .size(itemSizeService.findOneBySizeName("10.5"))
                .releaseDate(LocalDate.of(2018, 7, 17))
                .condition("New")
                .description("Joining together with Coca-Cola and Converse in July 2018, " +
                        "NYC-based Kith launched the Kith x Coca-Cola x Chuck 70 Hi 'Friends & Family' " +
                        "2018 contributing to a sneaker pack of the same name. " +
                        "The high-top silhouette is armed with a blue denim upper featuring " +
                        "an unfinished ankle and an overstated 'Coca-Cola' " +
                        "embroidered from the heel to the sidewall.")
                .brand(brandService.getBrandByName("Converse"))
                .style(styleService.getStyleByName(CASUAL))
                .itemColors(ItemColors.BLACK)
                .build();

        // Jordan
        ItemInfo jordan1RetroHighII = ItemInfo.builder()
                .item(itemService.getItemByName("Jordan 1 Retro High Satin Black Toe (W)"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 200.0"))
                .highestBid(Money.parse("USD 241.0"))
                .lowestAsk(Money.parse("USD 167.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfojordan1RetroHighBI1"))
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
                .brand(brandService.getBrandByName(JORDAN))
                .style(styleService.getStyleByName(SPORTS))
                .itemColors(ItemColors.BROWN)
                .build();

        ItemInfo jordan4RetroWinterizedII = ItemInfo.builder()
                .item(itemService.getItemByName("Jordan 4 Retro Winterized Loyal Blue"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 210.0"))
                .highestBid(Money.parse("USD 237.0"))
                .lowestAsk(Money.parse("USD 195.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfoJordan4RetroWinterizedBI1"))
                .size(itemSizeService.findOneBySizeName("10"))
                .releaseDate(LocalDate.of(2019, 12, 21))
                .condition("New")
                .description("Jordan Brand spins an iconic design for winter with the Jordan 4 Retro Winterized Loyal Blue, " +
                        "now available on StockX. Reminiscent of the extremely limited Eminem Encore 4, this release gives " +
                        "many of us the opportunity to own a blue toned Jordan 4 without having to spend more than the cost of a car. " +
                        "The difference between this winterized design and a traditional Jordan 4 lies in the material choices. " +
                        "The Winterized 4 replaces the classic mesh insert panels with a canvas-like material and adopts a fleece lining " +
                        "to retain warmth.")
                .brand(brandService.getBrandByName(JORDAN))
                .style(styleService.getStyleByName(SPORTS))
                .itemColors(ItemColors.WHITE)
                .build();

        ItemInfo jordan14RetroGymRedToroII = ItemInfo.builder()
                .item(itemService.getItemByName("Jordan 14 Retro Gym Red Toro"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 200.0"))
                .highestBid(Money.parse("USD 222.0"))
                .lowestAsk(Money.parse("USD 191.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfojordan14RetroGymRedToroBI1"))
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
                .brand(brandService.getBrandByName(JORDAN))
                .style(styleService.getStyleByName(SPORTS))
                .itemColors(ItemColors.BLACK)
                .build();

        // Louis Vuitton
        ItemInfo louisVuittonDonKanyeRedII = ItemInfo.builder()
                .item(itemService.getItemByName("Louis Vuitton Don Kanye Red"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("EUR 1978.0"))
                .highestBid(Money.parse("EUR 3380.0"))
                .lowestAsk(Money.parse("EUR 4945.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingLouisVuittonDonKanyeRedBI1"))
                .size(itemSizeService.findOneBySizeName("8"))
                .releaseDate(LocalDate.of(2009, 7, 3))
                .condition("New")
                .description("The Kanye West x Louis Vuitton Don 'Red' features a premium red leather upper with matching suede overlays.")
                .brand(brandService.getBrandByName("Louis Vuitton"))
                .style(styleService.getStyleByName(CASUAL))
                .itemColors(ItemColors.RED)
                .build();

        // New Balance
        ItemInfo newBalance990v3JJJJoundII = ItemInfo.builder()
                .item(itemService.getItemByName("New Balance 990v3 JJJJound"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 1500.0"))
                .highestBid(Money.parse("USD 1787.0"))
                .lowestAsk(Money.parse("USD 9654.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfoNewBalance990v3JJJJoundBI1"))
                .size(itemSizeService.findOneBySizeName("11"))
                .releaseDate(LocalDate.of(2018, 12, 3))
                .condition("New")
                .description("The neutral colorway is in keeping with Justin Saunders' signature aesthetic, " +
                        "and is sure to be attractive to those with more toned-down sensibilities.")
                .brand(brandService.getBrandByName("New Balance"))
                .style(styleService.getStyleByName(CASUAL))
                .itemColors(ItemColors.GRAY)
                .build();

        // Nike
        ItemInfo nikeReactElement87II = ItemInfo.builder()
                .item(itemService.getItemByName("Nike React Element 87 Anthracite Black"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 190.0"))
                .highestBid(Money.parse("USD 213.0"))
                .lowestAsk(Money.parse("USD 166.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfoNikeReactBI1"))
                .size(itemSizeService.findOneBySizeName("10"))
                .releaseDate(LocalDate.of(2018, 6, 14))
                .condition("New")
                .description("Since first being spotted on the runway during a Paris Fashion Week Show in March, " +
                        "the Nike React Element 87 has been of the most hyped shoes of 2018. Mimicking a Virgil Abloh-esque " +
                        "deconstructed style the react Element 87 features a transcluscent upper and a React-cushioned midsole. " +
                        "Released exclusively overseas in June, this pair saw an American release in July 2018 at a retail " +
                        "price of $160.")
                .brand(brandService.getBrandByName("Nike"))
                .style(styleService.getStyleByName(SPORTS))
                .itemColors(ItemColors.WHITE)
                .build();

        // Saucony
        ItemInfo sauconyAzuraBodegaLucky13II = ItemInfo.builder()
                .item(itemService.getItemByName("Saucony Azura Bodega Lucky 13"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("USD 800.0"))
                .highestBid(Money.parse("USD 166.0"))
                .lowestAsk(Money.parse("USD 800.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("buyingInfoSauconyAzuraBodegaLucky13BI1"))
                .size(itemSizeService.findOneBySizeName("12"))
                .releaseDate(LocalDate.of(2019, 5, 16))
                .condition("New")
                .description("The sneaker is predominantly black with dark pops of color throughout, " +
                        "including red on the medial Saucony branding and heel, purple on the tongue, " +
                        "and a multicolored outsole. " +
                        "Reflective detailing is present on the toe box, while the midsole is speckled.")
                .brand(brandService.getBrandByName("Saucony"))
                .style(styleService.getStyleByName(CASUAL))
                .itemColors(ItemColors.BLACK)
                .build();

        // Vans
        ItemInfo vansEraSupremeJeanPaulGaultierBurgundyII = ItemInfo.builder()
                .item(itemService.getItemByName("Vans Era Supreme Jean Paul Gaultier Burgundy"))
                .itemCategory(ItemCategory.SNEAKERS)
                .price(Money.parse("EUR 150.0"))
                .highestBid(Money.parse("EUR 2249.0"))
                .lowestAsk(Money.parse("EUR 124.0"))
                .buyingInfo(buyingInfoInit.createBuyingInfoForInitialization().get("vansEraSupremeJeanPaulGaultierBurgundyBI1"))
                .size(itemSizeService.findOneBySizeName("10.5"))
                .releaseDate(LocalDate.of(2019, 11, 1))
                .condition("New")
                .description("Supreme x Vans sneakers in beautiful design in collaboration with the trademark brand Jean Paul Gaultier.")
                .brand(brandService.getBrandByName("Vans"))
                .style(styleService.getStyleByName(CASUAL))
                .itemColors(ItemColors.BLACK)
                .build();

        Map<String, ItemInfo> itemInfoMap = Map.ofEntries(
                // Adidas
                new AbstractMap.SimpleEntry<>("adidasYeezyBoost380MistII", adidasYeezyBoost380MistII),
                // Converse
                new AbstractMap.SimpleEntry<>("converseAllStar70sHiKithxCocaColaII", converseAllStar70sHiKithxCocaColaII),
                // Jordan
                new AbstractMap.SimpleEntry<>("jordan1RetroHighII", jordan1RetroHighII),
                new AbstractMap.SimpleEntry<>("jordan4RetroWinterizedII", jordan4RetroWinterizedII),
                new AbstractMap.SimpleEntry<>("jordan14RetroGymRedToroII", jordan14RetroGymRedToroII),
                // Louis Vuitton
                new AbstractMap.SimpleEntry<>("louisVuittonDonKanyeRedII", louisVuittonDonKanyeRedII),
                // New Balance
                new AbstractMap.SimpleEntry<>("newBalance990v3JJJJoundII", newBalance990v3JJJJoundII),
                // Nike
                new AbstractMap.SimpleEntry<>("nikeReactElement87II", nikeReactElement87II),
                // Saucony
                new AbstractMap.SimpleEntry<>("sauconyAzuraBodegaLucky13II", sauconyAzuraBodegaLucky13II),
                // Vans
                new AbstractMap.SimpleEntry<>("vansEraSupremeJeanPaulGaultierBurgundyII", vansEraSupremeJeanPaulGaultierBurgundyII)
        );
        return new TreeMap<>(itemInfoMap);
    }
}