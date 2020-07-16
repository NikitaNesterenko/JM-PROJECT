package jm.stockx.auth.telegram_bot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Getter
@Setter
//@Configuration
//@ConfigurationProperties(prefix = "telegrambot")
public class StockJmAuthBotConfig {
    private String botUsername;
    private String botToken;
    private String botPath;

    @Bean
    StockJmAuthBot stockJmAuthBot() {

        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        StockJmAuthBot stockJmAuthBot = new StockJmAuthBot(options);

        stockJmAuthBot.setBotUsername(botUsername);
        stockJmAuthBot.setBotToken(botToken);
        stockJmAuthBot.setBotPath(botPath);

        return stockJmAuthBot;
    }
}
