package jm.stockx;

import com.stripe.model.Order;
import jm.stockx.entity.News;
import jm.stockx.entity.User;

import java.util.List;

public interface MailService {

    void sendSimpleMessage(String to, String subject, String text);

    boolean sendRecoveryLinkToUser(User user);

    boolean sendRegistrationLinkToUser(User user);

    boolean activateAccountByToken(String link);

    boolean changePasswordByToken(String link, String newPassword);

    boolean sendPasswordFromClient (User user);

    boolean sendOrderStatus (Order order);

    boolean sendLastNews (List<News> news);

}