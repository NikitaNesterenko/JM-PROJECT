package jm.stockx.auth.telegram_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StockJmAuthBot extends TelegramLongPollingBot {
    private final String TOKEN = "1312216826:AAHEbsg8l9SXLY2EDeuA0xgQeI60xZ37pd4";
    private final String USERNAME = "StockJmAuthBot";

    @Override
    public void onUpdateReceived(Update update) {

        if (update.getMessage() != null && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();

            try {
                execute(new SendMessage(chatId, "test message"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
