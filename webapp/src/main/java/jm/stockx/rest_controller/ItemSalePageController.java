package jm.stockx.rest_controller;

import jm.stockx.ItemSalePageDtoService;
import jm.stockx.dto.item.ItemSalePageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/item")
public class ItemSalePageController {
    private final ItemSalePageDtoService itemSalePageDtoService;

    @Autowired
    public ItemSalePageController(ItemSalePageDtoService itemSalePageDtoService) {
        this.itemSalePageDtoService = itemSalePageDtoService;
    }

    @GetMapping("/get")
    public ResponseEntity<ItemSalePageDto> getById(@RequestParam Long id) {

        return ResponseEntity.ok().body(itemSalePageDtoService.getById(id));
    }
}
