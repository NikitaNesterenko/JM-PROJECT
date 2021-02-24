package jm.stockx;

import jm.stockx.dto.paymentinfo.PaymentInfoDto;

public interface PaymentInfoService {
    void addPaymentInfoFromPaymentInfoDto(PaymentInfoDto paymentInfoDto);
}
