package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.user.UserEmailDto;
import jm.stockx.enums.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdvertisementEmailServiceImpl implements AdvertisementEmailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    private ItemInfoDAO itemInfoDAO;

    @Autowired
    private UserDAO userDAO;

    public void sendSimpleEmail(String toAddress, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
            messageHelper.addAttachment("Purchase Order", file);
            javaMailSender.send(mimeMessage);
        } catch(MessagingException | FileNotFoundException | javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailByReleaseDate(LocalDate releaseDate) {

        ItemCategory itemCategory = itemInfoDAO.getItemCategoryByReleaseDate(releaseDate);

        List<UserEmailDto> userEmailList = userDAO.getUserEmailByItemCategory(itemCategory);

        for (UserEmailDto userEmailDto : userEmailList) {
            sendSimpleEmail(userEmailDto.getEmail(), "New Item", "New Item released!");
        }
    }
}