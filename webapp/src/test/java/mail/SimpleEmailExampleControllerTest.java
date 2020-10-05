package mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailExampleControllerTest {
    @Mock
    public JavaMailSender emailSender;
    @Test
    public void sendSimpleEmail() {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("java.mentor.jp@gmail.com");
        message.setSubject("Тема письма");
        message.setText("Текст письма");
        // Send Message!
        this.emailSender.send(message);
    }

    @Test
    public void getDate() {
        System.out.println(getLocalDateTime());
    }

    private LocalDateTime getLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2020,
                Month.of((int)(1+Math.random()*11)),
                (int)(1 + Math.random()*30),
                (int) (Math.random()*24),
                (int) (Math.random()*59));
        return localDateTime;
    }
}
