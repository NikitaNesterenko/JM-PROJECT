package jm.stockx.rest_controller;

import jm.stockx.ItemInfoService;
import jm.stockx.ItemService;
import jm.stockx.dto.itemInfo.ItemSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class SearchRestController {
    private final ItemInfoService itemInfoService;

    @Autowired
    public SearchRestController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @GetMapping("/search")
    public List<ItemSearchDto> searchItem(@RequestParam(required = false, name = "s") String search) {
        return itemInfoService.getItemSearchDtoBySearch(search);
    }
}
