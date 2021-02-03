package jm.stockx.rest_controller;

import jm.stockx.ItemPriceChartService;
import jm.stockx.dto.ItemPriceChartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ItemPriceChartDto> get(@RequestParam Long id) {
        return new ResponseEntity<>(service.get12LatestSales(id), HttpStatus.OK);
    }

}
