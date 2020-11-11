package jm.stockx.rest_controller;

import jm.stockx.AdvertisementEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping("/email")
public class AdvertisementEmailController {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertisementEmailController.class);

    @Autowired
    AdvertisementEmailService advertisementEmailService;

    @GetMapping(value = "/simple-email/{user-email}")
    public @ResponseBody ResponseEntity sendSimpleEmail(@PathVariable("user-email") String email) {

        try {
            advertisementEmailService.sendSimpleEmail(email, "New Item", "New Items Released");
        } catch (MailException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    @GetMapping(value = "/simple-email/{release-date}")
    public @ResponseBody ResponseEntity sendEmailByReleaseDate(@PathVariable("release-date") LocalDate releaseDate) {
        try {
            advertisementEmailService.sendEmailByReleaseDate(releaseDate);
        } catch (MailException | MessagingException | FileNotFoundException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

    @GetMapping(value = "/simple-order-email/{user-email}")
    public @ResponseBody ResponseEntity sendEmailAttachment(@PathVariable("user-email") String email) {

        try {
            advertisementEmailService.sendEmailWithAttachment(email, "Order Confirmation",
                    "Thanks for your recent order", "classpath:purchase_order.pdf");
        } catch (MessagingException | FileNotFoundException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
    }
}
