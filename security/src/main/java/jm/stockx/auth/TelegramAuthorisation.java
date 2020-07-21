package jm.stockx.auth;

import jm.stockx.dto.TelegramUserDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TelegramAuthorisation {
    Logger logger = LoggerFactory.getLogger(TelegramAuthorisation.class);
    private final String botToken;

    @Autowired
    public TelegramAuthorisation(Environment environment) {
        botToken = environment.getProperty("telegramBot.botToken");
    }

    public Boolean isTelegramAccountDataRight(TelegramUserDTO telegramUserDTO) {
        String dataCheckString = toDataCheckString(telegramUserDTO);

        byte[] data = dataCheckString.getBytes();
        byte[] secret = DigestUtils.sha256(botToken);

        String telegramUserHash = telegramUserDTO.getHash();
        String dataSecretHash = HmacUtils.hmacSha256Hex(secret, data);

        logger.info("Telegram user hash!!!");
        logger.info(telegramUserHash);
        logger.info("Data secret hash!!!");
        logger.info(dataSecretHash);

        if (dataSecretHash.equals(telegramUserHash)) {
            logger.info("Telegram data Right!!!");
        } else {
            logger.info("Telegram data Wrong!!!");
        }

        return dataSecretHash.equals(telegramUserHash);
    }

    private String toDataCheckString(TelegramUserDTO telegramUserDTO) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("auth_date=").append(telegramUserDTO.getAuth_date()).append('\n')
                .append("first_name=").append(telegramUserDTO.getFirst_name()).append('\n')
                .append("id=").append(telegramUserDTO.getId()).append('\n')
                .append("last_name=").append(telegramUserDTO.getLast_name()).append('\n')
                .append("photo_url=").append(telegramUserDTO.getPhoto_url()).append('\n')
                .append("username=").append(telegramUserDTO.getUsername());

        return stringBuilder.toString();
    }

}
