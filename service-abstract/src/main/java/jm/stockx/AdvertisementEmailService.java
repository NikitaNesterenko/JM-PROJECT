package jm.stockx;

import java.time.LocalDate;

public interface AdvertisementEmailService {

    void sendSimpleEmail(final String toAddress, final String subject, final String message);


    void sendEmailWithAttachment(final String toAddress, final String subject,
                                 final String message, final String attachment);

    void sendEmailByReleaseDate(LocalDate releaseDate);
}
