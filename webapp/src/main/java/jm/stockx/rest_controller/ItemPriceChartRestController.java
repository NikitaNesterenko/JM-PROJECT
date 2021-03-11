package jm.stockx.rest_controller;

import jm.stockx.ItemPriceChartService;
import jm.stockx.dto.ItemPriceChartDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/chart")
public class ItemPriceChartRestController {
    private final ItemPriceChartService service;

    @Autowired
    public ItemPriceChartRestController(ItemPriceChartService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public Response<ItemPriceChartDto> get(@RequestParam Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        ItemPriceChartDto latestSales = service.get12LatestSales(id, userId);
        return Response.ok(latestSales);
    }
}
