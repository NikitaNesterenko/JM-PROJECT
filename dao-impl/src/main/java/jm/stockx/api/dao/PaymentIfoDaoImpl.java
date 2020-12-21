package jm.stockx.api.dao;

import jm.stockx.dto.paymentInfo.PaymentInfoDto;
import jm.stockx.entity.PaymentInfo;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentIfoDaoImpl extends AbstractDAO<PaymentInfo, Long> implements PaymentInfoDAO {
}
