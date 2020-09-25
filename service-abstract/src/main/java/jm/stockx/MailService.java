package jm.stockx;

import jm.stockx.entity.User;

public interface MailService {

    void sendSimpleMessage(String to, String subject, String text);

    boolean sendRecoveryLinkToUser(User user);

    boolean sendRegistrationLinkToUser(User user);

    boolean changePasswordByToken(String link, String newPassword);

}
