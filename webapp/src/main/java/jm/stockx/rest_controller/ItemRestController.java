package jm.stockx.rest_controller;

import jm.stockx.ItemInfoService;
import jm.stockx.dto.iteminfo.ItemInfoDtoDecimal;
import jm.stockx.dto.iteminfo.ItemSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
// TODO: Требуется изменение маппинга. Везде так: "/rest/api/названиеДомена"
public class ItemRestController {
    private final ItemInfoService itemInfoService;
    @Autowired
    public ItemRestController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @GetMapping("/search")
    public List<ItemSearchDto> searchItem(@RequestParam(required = false, name = "s") String search) {
        return itemInfoService.getItemSearchDtoBySearch(search);
    }

    @GetMapping("/allItem")
    public ResponseEntity<List<ItemInfoDtoDecimal>> getAllItemInfo() {

        List<ItemInfoDtoDecimal> list = itemInfoService.getAllItemInfoDtoDecimal();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/item")
    public ResponseEntity<ItemInfoDtoDecimal> getItemInfoDtoDec(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(itemInfoService.getItemInfoDtoDec(id));
    }

    /**
     * Получение самых продаваемых предметов.
     * Используется для заполнения компонента Main Page.
     *
     * @return List<NewsDto>
     */
    @GetMapping("/mostpopular")
    public ResponseEntity<List<ItemTopInfoDto>> getMostPopularItems() {
        List<ItemTopInfoDto> foundTodItems = sellingInfoService.getItemTopInfoDto(5);
        return ResponseEntity.ok(foundTodItems);
    }
}
