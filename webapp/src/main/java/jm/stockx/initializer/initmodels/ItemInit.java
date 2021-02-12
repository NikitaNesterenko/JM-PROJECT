package jm.stockx.initializer.initmodels;

import jm.stockx.ItemService;
import jm.stockx.entity.Item;
import jm.stockx.entity.SneakersItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс для создания вещи в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class ItemInit {
    private final ItemService itemService;

    public void initializeItems() {
        Map<String, Item> itemsForCreation = createItemsForInitialization();
        itemsForCreation.values().forEach(itemService::create);
    }

    private Map<String, Item> createItemsForInitialization() {
        Map<String, Item> itemMap = Map.ofEntries(
                // Adidas
                new AbstractMap.SimpleEntry<>("adidasYeezyBoost380Mist", new SneakersItem(("Adidas Yeezy Boost 380 Mist"))),
                // Converse
                new AbstractMap.SimpleEntry<>("converseAllStar70sHiKithxCocaCola", new SneakersItem(("Converse Chuck Taylor All Star 70s Hi Kith x Coca Cola Golden Rod/Dress Blues (Friends and Family)"))),
                // Jordan
                new AbstractMap.SimpleEntry<>("jordan1RetroHigh", new SneakersItem(("Jordan 1 Retro High Satin Black Toe (W)"))),
                new AbstractMap.SimpleEntry<>("jordan4RetroWinterized", new SneakersItem(("Jordan 4 Retro Winterized Loyal Blue"))),
                new AbstractMap.SimpleEntry<>("jordan14RetroGymRedToro", new SneakersItem(("Jordan 14 Retro Gym Red Toro"))),
                // Louis Vuitton
                new AbstractMap.SimpleEntry<>("louisVuittonDonKanyeRed", new SneakersItem(("Louis Vuitton Don Kanye Red"))),
                // New Balance
                new AbstractMap.SimpleEntry<>("newBalance990v3JJJJound", new SneakersItem(("New Balance 990v3 JJJJound"))),
                // Nike
                new AbstractMap.SimpleEntry<>("nikeReactElement87", new SneakersItem(("Nike React Element 87 Anthracite Black"))),
                // Saucony
                new AbstractMap.SimpleEntry<>("sauconyAzuraBodegaLucky13", new SneakersItem(("Saucony Azura Bodega Lucky 13"))),
                // Vans
                new AbstractMap.SimpleEntry<>("vansEraSupremeJeanPaulGaultierBurgundy", new SneakersItem(("Vans Era Supreme Jean Paul Gaultier Burgundy")))
        );
        return new TreeMap<>(itemMap);
    }
}