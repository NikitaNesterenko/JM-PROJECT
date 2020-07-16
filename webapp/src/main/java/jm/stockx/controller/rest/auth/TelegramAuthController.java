package jm.stockx.controller.rest.auth;

import jm.stockx.auth.telegram_bot.StockJmAuthBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

//@RestController
public class TelegramAuthController {
    private final StockJmAuthBot stockJmAuthBot;

    @Autowired
    public TelegramAuthController(StockJmAuthBot stockJmAuthBot) {
        this.stockJmAuthBot = stockJmAuthBot;
    }

    @RequestMapping(value = "/telegramAuth", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return stockJmAuthBot.onWebhookUpdateReceived(update);
    }
}
