package jm.stockx.rest_controller;

import jm.stockx.BuyingInfoService;
import jm.stockx.ItemInfoService;
import jm.stockx.MailService;
import jm.stockx.UserService;
import jm.stockx.dto.buyingInfo.BuyingInfoPostDto;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.User;
import jm.stockx.enums.Status;
import jm.stockx.util.Response;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/buying")
public class BuyingRestController {
    private UserService userService;
    private MailService mailService;
    private BuyingInfoService buyingInfoService;

    @Autowired
    public BuyingRestController(UserService userService, MailService mailService, BuyingInfoService buyingInfoService) {
        this.userService = userService;
        this.mailService = mailService;
        this.buyingInfoService = buyingInfoService;
    }

    @PostMapping("/add")
    public Response<?> addBuyingInfo (@RequestBody BuyingInfoPostDto buyingInfoPostDto) {

        Long id = buyingInfoService.create(buyingInfoPostDto);

        BuyingInfo buyingInfo = buyingInfoService.getBuyingInfoById(id);

        Set<BuyingInfo> buyingInfos= new HashSet<>();
        buyingInfos.add(buyingInfo);

        User user = userService.getUserByUsername(SecurityContextHolder.getContext()
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
