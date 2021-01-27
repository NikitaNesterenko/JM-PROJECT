package jm.stockx.rest_controller;

import jm.stockx.MailService;
import jm.stockx.UserService;
import jm.stockx.dto.buyingInfo.BuyingInfoPostDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/buying")
public class BuyingRestController {
    private BuyingInfo buyingInfo;
    private UserService userService;
    private User user;
    private MailService mailService;

    @Autowired
    public BuyingRestController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @PostMapping("/add")
    public Response<?> addBuyingInfo (@RequestBody BuyingInfoPostDto buyingInfoPostDto) {

        buyingInfo = BuyingInfo.builder()
                .buyingPrice(buyingInfoPostDto.getBuyingPrice())
                .paymentsInfo(buyingInfoPostDto.getPaymentsInfo())
                .buyingTimeStamp(buyingInfoPostDto.getBuyingTimeStamp())
                .status(buyingInfoPostDto.getStatus())
                .boughtItemsInfo(buyingInfoPostDto.getBoughtItems())
                .build();

        Set<BuyingInfo> buyingInfos= new HashSet<>();
        buyingInfos.add(buyingInfo);

        user = userService.getUserByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
        user.setBuyingInfo(buyingInfos);
        userService.updateUser(user);

        StringBuilder message = new StringBuilder("Congratulations! You have bought the best products:\n");
        for (ItemInfo i:buyingInfo.getBoughtItemsInfo()) {
            message.append(i.getItem().getName()).append("\n");
        }
        mailService.sendSimpleMessage(user.getEmail(), "Your best buy!", message.toString());

        return Response.ok().build();
    }
}
