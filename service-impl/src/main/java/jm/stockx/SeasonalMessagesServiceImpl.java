package jm.stockx;

import jm.stockx.api.dao.BrandDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@EnableScheduling
public class SeasonalMessagesServiceImpl implements SeasonalMessagesService{
    private final MailServiceImpl mailService;
    private final UserService userService;
    private final BrandService brandService;

    public SeasonalMessagesServiceImpl(MailServiceImpl mailService, UserService userService, BrandService brandService) {
        this.mailService = mailService;
        this.userService = userService;
        this.brandService = brandService;
    }

    @Override
    @Scheduled(cron = "* * * 23 AUG ?")
    public void messageAboutStartAutumnWinterSeason() {
        mailingSeasonalMessages("Сообщение о начале сезона",
                "Стартовал сезон Осень - Зима продаж бренда ");
        log.info("Сообщение о начале сезона Осень - Зима отправлено всем пользователям");
    }

    @Override
    @Scheduled(cron = "* * * 31 JAN ?")
    public void messageAboutEndAutumnWinterSeason() {
        mailingSeasonalMessages("Сообщение об окончании сезона",
                "Закончился сезон Осень - Зима продаж бренда ");
        log.info("Сообщение об окончании сезона Осень - Зима отправлено всем пользователям");
    }

    @Override
    @Scheduled(cron = "* * * 23 FEB ?")
    public void messageAboutStartSpringSummerSeason() {
        mailingSeasonalMessages("Сообщение о начале сезона",
                "Стартовал сезон Весна - Лето продаж бренда ");
        log.info("Сообщение о начале сезона Весна - Лето отправлено всем пользователям");
    }

    @Override
    @Scheduled(cron = "* * * 31 JUL ?")
    public void messageAboutEndSpringSummerSeason() {
        mailingSeasonalMessages("Сообщение об окончании сезона",
                "Закончился сезон Весна - Лето продаж бренда ");
        log.info("Сообщение об окончании сезона Весна - Лето отправлено всем пользователям");
    }

    private void mailingSeasonalMessages(String subject, String text) {
        for (String brand : brandService.getAllBrandNames()) {
            for (String string : userService.getAllUsersMail()) {
                mailService.sendSimpleMessage(string, subject, text.concat(brand));
            }
        }
    }
}
