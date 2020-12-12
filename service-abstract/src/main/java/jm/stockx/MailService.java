package jm.stockx;

import com.stripe.model.Order;
import jm.stockx.entity.News;
import jm.stockx.entity.User;

import java.util.List;

public interface MailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageFrom(String to, String from, String password, String subject, String text);

    boolean sendRecoveryLinkToUser(User user);

    boolean sendRegistrationLinkToUser(User user);

    boolean activateAccountByToken(String link);

    boolean changePasswordByToken(String link, String newPassword);

    void sendPasswordFromClient(User user, String sourceMail, String password);

    void sendOrderStatus (Order order, String sourceMail, String password);

    void sendLastNews (List<News> news, String sourceMail, String password);

}