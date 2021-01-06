package jm.stockx.rest_controller;

import jm.stockx.ItemInfoService;
import jm.stockx.dto.itemInfo.ItemInfoDto;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@ResponseBody
@RequestMapping(value = "/api/item")
public class SalesPageController {
    private final ItemInfoService itemInfoService;

    @Autowired
    public SalesPageController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @PostMapping("/info")
    public ItemInfoDto getItemInfoDto(@RequestParam(name = "id") Long id){
        ItemInfo itemInfo= itemInfoService.get(id);
        ItemInfoDto itemInfoDto = new ItemInfoDto(itemInfo);
        return itemInfoDto;
    }

}
