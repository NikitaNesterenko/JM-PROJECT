package jm.stockx.rest_controller;

import jm.stockx.UserService;
import jm.stockx.dto.buyingInfo.BuyingInfoPostDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
