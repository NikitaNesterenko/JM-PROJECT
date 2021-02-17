package jm.stockx.initializer.initmodels;

import jm.stockx.StyleService;
import jm.stockx.entity.Style;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания стилей в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class StyleInit {
    private final StyleService styleService;

    public void initializeStyles() {
        List<Style> stylesListForCreation = createStylesForInitialization();
        stylesListForCreation.forEach(styleService::create);
    }

    private List<Style> createStylesForInitialization() {
        return Arrays.asList(
                new Style("casual"),
                new Style("sports"),
                new Style("business"));
    }
}