package jm.stockx.rest_controller;

import jm.stockx.ItemService;
import jm.stockx.dto.item.ItemSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchRestController {
    final ItemService itemService;

    @Autowired
    public SearchRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/search")
    public List<ItemSearchDto> searchItem(@RequestParam(required = false, name = "s") String search) {
        return itemService.getItemSearchDtoBySearch(search);
    }
}
