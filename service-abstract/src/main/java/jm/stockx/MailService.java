package jm.stockx;

public interface MailService {

    void sendSimpleMessage(String to, String subject, String text);

    boolean sendRecoveryLinkToUser(User user);

    boolean changePasswordByToken(String link, String newPassword);

}
