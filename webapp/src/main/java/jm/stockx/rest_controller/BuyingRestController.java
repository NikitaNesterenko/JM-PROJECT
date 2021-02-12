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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/buying")
public class BuyingRestController {
    private UserService userService;

    @Autowired
    public BuyingRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Response<?> addBuyingInfo (@RequestBody BuyingInfoPostDto buyingInfoPostDto) {
        userService.addBuyingInfo(buyingInfoPostDto);
        return Response.ok().build();
    }
}
