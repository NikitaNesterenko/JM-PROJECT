package jm.stockx.auth;

import jm.stockx.RoleService;
import jm.stockx.UserService;
import jm.stockx.dto.TelegramUserDTO;
import jm.stockx.entity.User;
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
    //TODO: заглушка для токена, указываем в пропертях
    private final String botToken = "";

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TelegramAuthorisation(Environment environment, UserService userService, RoleService roleService) {
        //TODO: в пропертях указываем токен бота
//        botToken = environment.getProperty("telegramBot.botToken");
        this.userService = userService;
        this.roleService = roleService;
    }

    private Boolean isTelegramAccountDataRight(TelegramUserDTO telegramUserDTO) {
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

    private User toTelegramUser(TelegramUserDTO telegramUserDTO) {
        User telegramUser = null;

        if (userService.getUserByUserName(telegramUserDTO.getUsername()) == null) {
            telegramUser = new User(telegramUserDTO.getFirst_name(), telegramUserDTO.getLast_name(),
                    telegramUserDTO.getUsername(), telegramUserDTO.getHash());
            telegramUser.setRole(roleService.get(2L));
            userService.createUser(telegramUser);
        } else {
            telegramUser = userService.getUserByUserName(telegramUserDTO.getUsername());
        }

        return telegramUser;
    }

    public boolean loginTelegramUser(TelegramUserDTO telegramUserDTO) {
        if (isTelegramAccountDataRight(telegramUserDTO)) {
            User telegramUser = toTelegramUser(telegramUserDTO);
            userService.login(telegramUser.getUsername(), telegramUser.getPassword(), telegramUser.getAuthorities());
            return true;
        }

        return false;
    }

}
