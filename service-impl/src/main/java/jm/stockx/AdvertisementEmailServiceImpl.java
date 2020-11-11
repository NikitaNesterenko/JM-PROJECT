package jm.stockx;

import jm.stockx.api.dao.BuyingInfoDAO;
import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.User;
import jm.stockx.enums.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdvertisementEmailServiceImpl implements AdvertisementEmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private ItemInfoDAO itemInfoDAO;

    @Autowired
    private BuyingInfoDAO buyingInfoDAO;

    @Autowired
    private UserDAO userDAO;


    @Override
    public void sendSimpleEmail(String toAddress, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
            throws MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("Purchase Order", file);
        emailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailByReleaseDate(LocalDate releaseDate) {
        ItemCategory itemCategory = itemInfoDAO.getItemCategoryByReleaseDate(releaseDate);
        ItemInfo itemInfo = itemInfoDAO.getItemInfoByItemCategory(itemCategory);
        BuyingInfo buyingInfo = buyingInfoDAO.getBuyingInfoByItemInfo(itemInfo);
        List<User> users = userDAO.getUsersByBuyingInfo(buyingInfo);

        for (User user : users) {
            sendSimpleEmail(user.getEmail(), "New Item", "New Item released!");
        }
    }
}
