package jm.stockx.rest_controller;

import jm.stockx.ItemInfoService;
import jm.stockx.SellingInfoService;
import jm.stockx.dto.itemInfo.ItemInfoDtoDecimal;
import jm.stockx.dto.itemInfo.ItemSearchDto;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
// TODO: Требуется изменение маппинга. Везде так: "/rest/api/названиеДомена"
public class ItemRestController {
    private final ItemInfoService itemInfoService;
    private final SellingInfoService sellingInfoService;

    public ItemRestController(ItemInfoService itemInfoService,
                              SellingInfoService sellingInfoService) {
        this.itemInfoService = itemInfoService;
        this.sellingInfoService = sellingInfoService;
    }

    // TODO: Требуется изменение return type на Response.
    // Я не трогал, так как кто-то сейчас занимается рефакторингом поиска.
    @GetMapping("/search")
    public List<ItemSearchDto> searchItem(@RequestParam(required = false) String search) {
        return itemInfoService.getItemSearchDtoBySearch(search);
    }

    @GetMapping("/allItem")
    public Response<List<ItemInfoDtoDecimal>> getAllItemInfo() {

        List<ItemInfoDtoDecimal> list = itemInfoService.getAllItemInfoDtoDecimal();
        return Response.ok(list);
    }

    @GetMapping("/item")
    public Response<ItemInfoDtoDecimal> getItemInfoDtoDec(@RequestParam(name = "id") Long id) {
        return Response.ok(itemInfoService.getItemInfoDtoDec(id));
    }

    /**
     * Получение самых продаваемых предметов.
     * Используется для заполнения компонента Main Page.
     *
     * @return List<NewsDto>
     */
    @GetMapping("/mostpopular")
    public Response<List<ItemTopInfoDto>> getMostPopularItems() {
        List<ItemTopInfoDto> foundTodItems = sellingInfoService.getItemTopInfoDto(5);
        return Response.ok(foundTodItems);
    }
}