package jm.stockx.auth.telegram_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Component
public class StockJmAuthBotConfig {
    private final String botUsername;
    private final String botToken;

    @Autowired
    public StockJmAuthBotConfig(Environment environment) {
        botUsername = environment.getProperty("telegramBot.botUsername");
        botToken = environment.getProperty("telegramBot.botToken");
        startBot();
    }

    void startBot() {
        //TODO: Telegram бот в регистрации не участвует, его можно включить позже,
        // раскомментировав строчки, и настроить логику в StockJmAuthBot и методе onUpdateReceived,
        // имя и токен бота указываются в пропертях, а также имя указано на странице login-with-telegram

//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        try {
//            StockJmAuthBot stockJmAuthBot = new StockJmAuthBot();
//            stockJmAuthBot.setBotUsername(botUsername);
//            stockJmAuthBot.setBotToken(botToken);
//            telegramBotsApi.registerBot(stockJmAuthBot);
//        } catch (TelegramApiRequestException e) {
//            e.printStackTrace();
//        }
    }
}
