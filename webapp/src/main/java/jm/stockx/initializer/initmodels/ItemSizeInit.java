package jm.stockx.initializer.initmodels;

import jm.stockx.ItemSizeService;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания размеров обуви в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class ItemSizeInit {
    private final ItemSizeService itemSizeService;

    public void initializeItemSizes() {
        List<ItemSize> itemSizesForCreation = createItemSizesForInitialization();
        itemSizesForCreation.forEach(itemSizeService::create);
    }

    private List<ItemSize> createItemSizesForInitialization() {
        return Arrays.asList(
                new ItemSize("7", ItemSizeTypes.MEN),
                new ItemSize("7.5", ItemSizeTypes.MEN),
                new ItemSize("8", ItemSizeTypes.MEN),
                new ItemSize("8.5", ItemSizeTypes.MEN),
                new ItemSize("9", ItemSizeTypes.MEN),
                new ItemSize("9.5", ItemSizeTypes.MEN),
                new ItemSize("10", ItemSizeTypes.MEN),
                new ItemSize("10.5", ItemSizeTypes.MEN),
                new ItemSize("11", ItemSizeTypes.MEN),
                new ItemSize("12", ItemSizeTypes.MEN),
                new ItemSize("12.5", ItemSizeTypes.MEN),
                new ItemSize("13", ItemSizeTypes.MEN),
                new ItemSize("14", ItemSizeTypes.MEN),
                new ItemSize("15", ItemSizeTypes.MEN),
                new ItemSize("16", ItemSizeTypes.MEN),
                new ItemSize("17", ItemSizeTypes.MEN),
                new ItemSize("18", ItemSizeTypes.MEN)
        );
    }
}