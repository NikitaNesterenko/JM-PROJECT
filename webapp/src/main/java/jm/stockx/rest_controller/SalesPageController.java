package jm.stockx.rest_controller;

import jm.stockx.ItemInfoService;
import jm.stockx.dto.iteminfo.ItemInfoDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.util.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/item")
public class SalesPageController {
    private final ItemInfoService itemInfoService;

    public SalesPageController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @PostMapping("/info")
    public Response<ItemInfoDto> getItemInfoDto(@RequestParam(name = "id") Long id) {
        ItemInfo foundItem = itemInfoService.get(id);
        ItemInfoDto itemInfoDto = new ItemInfoDto(foundItem);
        return Response.ok(itemInfoDto);
    }
}