package jm.stockx.rest_controller;

import jm.stockx.ItemPriceChartService;
import jm.stockx.dto.ItemPriceChartDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
        ItemPriceChartDto latestSales = service.get12LatestSales(id);
        return Response.ok(latestSales);
    }
}
