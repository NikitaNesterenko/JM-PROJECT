package jm.stockx;

import jm.stockx.api.dao.PaymentInfoDAO;
import jm.stockx.dto.paymentinfo.PaymentInfoDto;
import jm.stockx.entity.PaymentInfo;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService{

    private final PaymentInfoDAO paymentInfoDAO;

    public PaymentInfoServiceImpl(PaymentInfoDAO paymentInfoDAO) {
        this.paymentInfoDAO = paymentInfoDAO;
    }

//    TODO ?
    @Override
    public void addPaymentInfoFromPaymentInfoDto(PaymentInfoDto paymentInfoDto) {
        paymentInfoDAO.add(new PaymentInfo(paymentInfoDto.getId(), paymentInfoDto.getCardNumber(),
                paymentInfoDto.getCardExpiresDate(), paymentInfoDto.getCvv(), paymentInfoDto.getFirstName(),
                paymentInfoDto.getLastName(), paymentInfoDto.getCountry(), paymentInfoDto.getAddress(),
                paymentInfoDto.getCity(), paymentInfoDto.getState(), paymentInfoDto.getZipCode(),
                paymentInfoDto.getPhoneNumber()));
    }
}
