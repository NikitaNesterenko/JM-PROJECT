package jm.stockx.initializer.initmodels;

import jm.stockx.NewsService;
import jm.stockx.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания новостей в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class NewsInit {
    public static final String TEXT_FILLER = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
            "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
            "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
            "mollit anim id est laborum.";
    private final NewsService newsService;

    public void initializeNews() {
        List<News> itemsForCreation = createNewsForInitialization();
        itemsForCreation.forEach(newsService::create);
    }

    private List<News> createNewsForInitialization() {
        News news1 = new News(
                "The Buyer's Guide: Air Jordan 13",
                LocalDateTime.of(2021, 2, 2, 7, 30),
                "The Buyer's Guide: Air Jordan 13",
                "The Buyer's Guide: Air Jordan 131",
                TEXT_FILLER);

        News news2 = new News(
                "That's 5 | Masiwei",
                LocalDateTime.of(2021, 2, 4, 2, 23),
                "That's 5 | Masiwei",
                "That's 5 | Masiwei1",
                TEXT_FILLER);

        News news3 = new News(
                "The Best Supreme Toys On StockX",
                LocalDateTime.of(2021, 2, 5, 2, 23),
                "The Best Supreme Toys On StockX",
                "The Best Supreme Toys On StockX1",
                TEXT_FILLER);

        News news4 = new News(
                "Sneakers: Then and Now",
                LocalDateTime.of(2021, 2, 6, 2, 23),
                "Sneakers: Then and Now",
                "Sneakers: Then and Now1",
                TEXT_FILLER);

        News news5 = new News(
                "Five Years of StockX",
                LocalDateTime.of(2021, 2, 9, 2, 23),
                "Five Years of StockX",
                "Five Years of StockX1",
                TEXT_FILLER);

        News news6 = new News(
                "The Flip: Edition 3",
                LocalDateTime.of(2021, 2, 8, 2, 23),
                "The Flip: Edition 3",
                "The Flip: Edition 31",
                "StockX saw record-breaking growth in 2020 as worldwide demand reached new highs. With active buyers on StockX up 90% year-over-year, we expect 2021 to be even bigger for all our sellers. \n" +
                        "\n" +
                        "Stay in the know about everything going on in the world of selling on StockX with our latest and greatest edition of The Flip.");

        return Arrays.asList(
                news1,
                news2,
                news3,
                news4,
                news5,
                news6
        );
    }
}
