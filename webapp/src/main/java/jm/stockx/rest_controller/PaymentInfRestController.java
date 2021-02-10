package jm.stockx.rest_controller;

import jm.stockx.PaymentInfoServiceImpl;
import jm.stockx.dto.paymentInfo.PaymentInfoDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paymentsinfo/")
public class PaymentInfRestController {
    private final PaymentInfoServiceImpl paymentInfoService;

    @Autowired
    public PaymentInfRestController(PaymentInfoServiceImpl paymentInfoService) {
        this.paymentInfoService = paymentInfoService;
    }

    // TODO: Неясен статус метода и контроллера в целом.
    @PostMapping("/add")
    public Response<Void> addPaymentInformation(@RequestBody PaymentInfoDto paymentInfoDto) {
        // paymentInfoService.updateFromPaymentInfoDto(paymentInfoDto);
        return Response.ok().build();
    }
}
