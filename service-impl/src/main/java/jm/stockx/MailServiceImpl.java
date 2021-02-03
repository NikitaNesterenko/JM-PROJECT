package jm.stockx;

import com.stripe.model.Order;
import jm.stockx.api.dao.TokenActivationDAO;
import jm.stockx.entity.News;
import jm.stockx.entity.TokenRecovery;
import jm.stockx.entity.TokenRegistration;
import jm.stockx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MailServiceImpl implements MailService {

    public final JavaMailSender emailSender;

    public final UserService userService;

    public final TokenRecoveryService tokenRecoveryService;

    public final TokenActivationDAO tokenActivation;

    @Value("${recovery.url}")
    private String urlRecoveryLink;

    @Value("${recovery.expirationDays}")
    private int linkExpirationDays;

    @Value("${registration.url}")
    private String urlRegistrationLink;

    private final JavaMailSenderImpl javaMailSender;


    @Autowired
    public MailServiceImpl(JavaMailSender emailSender, TokenRecoveryService tokenRecoveryService,
                           UserService userService, TokenActivationDAO tokenActivation, JavaMailSenderImpl javaMailSender) {
        this.emailSender = emailSender;
        this.tokenRecoveryService = tokenRecoveryService;
        this.userService = userService;
        this.tokenActivation = tokenActivation;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendSimpleMessageFrom(String to, String from, String password, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.setUsername(from);
        javaMailSender.setPassword(password);
        javaMailSender.send(message);
    }

    @Override
    public boolean sendRecoveryLinkToUser(User user) {
        if (user.getEmail() == null) {
            return false;
        }
        String hash = UUID.randomUUID().toString();
        String hashEmail = urlRecoveryLink + hash;
        TokenRecovery token = new TokenRecovery();
        token.setUser(user);
        token.setHash(hash);
        token.setHashEmail(hashEmail);
        token.setStartTime(getCurrentDate());
        try {
            sendSimpleMessage(user.getEmail(), "Password recovery", hashEmail);
            tokenRecoveryService.createToken(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sendRegistrationLinkToUser(User user) {
        if (user.getEmail() == null) {
            return false;
        }
        String hash = UUID.randomUUID().toString();
        String hashEmail = urlRegistrationLink + hash;
        TokenRegistration token = new TokenRegistration();
        token.setUser(user);
        token.setHash(hash);
        token.setHashEmail(hashEmail);
        token.setStartTime(getCurrentDate());

        try {
            sendSimpleMessage(user.getEmail(), "Confirm your registration", hashEmail);
            tokenActivation.save(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean changePasswordByToken(String link, String password) throws RecoveryException {
        TokenRecovery token = tokenRecoveryService.getTokenRecoveryByHashEmail(link);
        if (token != null && isValidToken(token.getStartTime())) {
            User user = token.getUser();
            user.setPassword(password);
            try {
                userService.updateUser(user);
                tokenRecoveryService.deleteToken(token.getId());
                return true;
            } catch (Exception e) {
                throw new RecoveryException();
            }
        }
        throw new RecoveryException();
    }




    @Override
    public boolean activateAccountByToken(String link) throws UserNotFoundException {
        TokenRegistration token = tokenActivation.getByHashEmail(link);
        if (token != null && isValidToken(token.getStartTime())) {

            try {
                tokenActivation.delete(token);
                token.getUser().setActive(true);
                return true;
            } catch (Exception ex) {
                throw new UserNotFoundException();
            }
        }
        throw new UserNotFoundException();
    }

    private Date getCurrentDate() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private boolean isValidToken(Date startDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDay);
        c.add(Calendar.DATE, linkExpirationDays);
        Date validDateCreate = c.getTime();
        return validDateCreate.after(getCurrentDate());
    }

    @Override
    public void sendPasswordFromClient(User user, String sourceMail, String password) {
        sendSimpleMessageFrom(user.getEmail(), sourceMail, password, user.getPassword(), "Your password");

    }

    @Override
    public void sendOrderStatus(Order order, String sourceMail, String password) {
        sendSimpleMessageFrom(order.getEmail(), sourceMail, password, order.getStatus(), "Your order status");
    }

    @Override
    public void sendLastNews(List<News> news, String sourceMail, String password) {
        //TODO тут, наверно, что-то должно быть
    }


}