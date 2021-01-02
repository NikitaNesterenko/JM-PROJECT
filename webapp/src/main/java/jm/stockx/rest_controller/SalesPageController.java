package jm.stockx.rest_controller;

import jm.stockx.ItemInfoService;
import jm.stockx.dto.itemInfo.ItemInfoDto;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/sales")
public class SalesPageController {
    private final ItemInfoService itemInfoService;

    @Autowired
    public SalesPageController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @GetMapping("/page")
    public ItemInfoDto getItemInfoDto(@RequestParam(name = "id") Long id){
        ItemInfo itemInfo= itemInfoService.get(id);
        ItemInfoDto itemInfoDto = new ItemInfoDto();
        itemInfoDto.setItemId(itemInfo.getId());
        itemInfoDto.setSize(String.valueOf(itemInfo.getSize()));
        itemInfoDto.setHighestBid(itemInfo.getHighestBid());
        itemInfoDto.setPrice(itemInfo.getPrice());
        itemInfoDto.setLowestAsk(itemInfo.getLowestAsk());
        return itemInfoDto;
    }

}
