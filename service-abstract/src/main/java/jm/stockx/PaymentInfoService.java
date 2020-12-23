package jm.stockx;

import jm.stockx.dto.paymentInfo.PaymentInfoDto;
import jm.stockx.entity.PaymentInfo;

public interface PaymentInfoService {
    void addPaymentInfoFromPaymentInfoDto(PaymentInfoDto paymentInfoDto);
}
