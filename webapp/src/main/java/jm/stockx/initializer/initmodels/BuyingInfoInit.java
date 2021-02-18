package jm.stockx.initializer.initmodels;

import jm.stockx.BuyingInfoService;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.enums.Status;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс для создания вещи в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class BuyingInfoInit {
    public Map<String, BuyingInfo> createBuyingInfoForInitialization() {
        // Adidas
        BuyingInfo buyingInfoAdidasYeezyBoost380MistBI = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 250.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.now())
                .build();

        // Converse
        BuyingInfo converseAllStar70sHiKithxCocaColaBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("EUR 1651.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2019, Month.AUGUST, 16, 14, 47))
                .build();

        BuyingInfo converseAllStar70sHiKithxCocaColaBI2 = BuyingInfo.builder()
                .buyingPrice(Money.parse("EUR 826.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2018, Month.NOVEMBER, 29, 16, 39))
                .build();

        // Jordan
        BuyingInfo buyingInfojordan1RetroHighBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 214.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.now())
                .build();

        BuyingInfo buyingInfoJordan4RetroWinterizedBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 220.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.now())
                .build();

        BuyingInfo buyingInfojordan14RetroGymRedToroBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("RUB 205.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.now())
                .build();

        // Louis Vuitton
        BuyingInfo buyingLouisVuittonDonKanyeRedBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 2144.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2021, Month.FEBRUARY, 2, 11, 53))
                .build();

        // New Balance
        BuyingInfo buyingInfoNewBalance990v3JJJJoundBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 3501.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.now())
                .build();

        // Nike
        BuyingInfo buyingInfoNikeReactBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 200.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2020, Month.JANUARY, 21, 11, 53))
                .build();

        BuyingInfo buyingInfoNikeReactBI2 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 201.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2019, Month.JANUARY, 22, 11, 53))
                .build();

        // Saucony
        BuyingInfo buyingInfoSauconyAzuraBodegaLucky13BI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 801.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2021, Month.JANUARY, 21, 11, 53))
                .build();

        BuyingInfo buyingInfoSauconyAzuraBodegaLucky13BI2 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 600.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2020, Month.OCTOBER, 25, 11, 53))
                .build();

        // Vans
        BuyingInfo vansEraSupremeJeanPaulGaultierBurgundyBI1 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 215.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2020, Month.OCTOBER, 19, 11, 53))
                .build();

        BuyingInfo vansEraSupremeJeanPaulGaultierBurgundyBI2 = BuyingInfo.builder()
                .buyingPrice(Money.parse("USD 200.0"))
                .status(Status.DELIVERED)
                .buyingTimeStamp(LocalDateTime.of(2021, Month.JANUARY, 30, 11, 53))
                .build();

        Map<String, BuyingInfo> buyingInfoMap = Map.ofEntries(
                // Adidas
                new AbstractMap.SimpleEntry<>("buyingInfoAdidasYeezyBoost380MistBI", buyingInfoAdidasYeezyBoost380MistBI),
                // Converse
                new AbstractMap.SimpleEntry<>("converseAllStar70sHiKithxCocaColaBI1", converseAllStar70sHiKithxCocaColaBI1),
                new AbstractMap.SimpleEntry<>("converseAllStar70sHiKithxCocaColaBI2", converseAllStar70sHiKithxCocaColaBI2),
                // Jordan
                new AbstractMap.SimpleEntry<>("buyingInfojordan1RetroHighBI1", buyingInfojordan1RetroHighBI1),
                new AbstractMap.SimpleEntry<>("buyingInfoJordan4RetroWinterizedBI1", buyingInfoJordan4RetroWinterizedBI1),
                new AbstractMap.SimpleEntry<>("buyingInfojordan14RetroGymRedToroBI1", buyingInfojordan14RetroGymRedToroBI1),
                // Louis Vuitton
                new AbstractMap.SimpleEntry<>("buyingLouisVuittonDonKanyeRedBI1", buyingLouisVuittonDonKanyeRedBI1),
                // New Balance
                new AbstractMap.SimpleEntry<>("buyingInfoNewBalance990v3JJJJoundBI1", buyingInfoNewBalance990v3JJJJoundBI1),
                // Nike
                new AbstractMap.SimpleEntry<>("buyingInfoNikeReactBI1", buyingInfoNikeReactBI1),
                new AbstractMap.SimpleEntry<>("buyingInfoNikeReactBI2", buyingInfoNikeReactBI2),
                // Saucony
                new AbstractMap.SimpleEntry<>("buyingInfoSauconyAzuraBodegaLucky13BI1", buyingInfoSauconyAzuraBodegaLucky13BI1),
                new AbstractMap.SimpleEntry<>("buyingInfoSauconyAzuraBodegaLucky13BI2", buyingInfoSauconyAzuraBodegaLucky13BI2),
                // Vans
                new AbstractMap.SimpleEntry<>("vansEraSupremeJeanPaulGaultierBurgundyBI1", vansEraSupremeJeanPaulGaultierBurgundyBI1),
                new AbstractMap.SimpleEntry<>("vansEraSupremeJeanPaulGaultierBurgundyBI2", vansEraSupremeJeanPaulGaultierBurgundyBI2)
        );
        return new TreeMap<>(buyingInfoMap);
    }
}