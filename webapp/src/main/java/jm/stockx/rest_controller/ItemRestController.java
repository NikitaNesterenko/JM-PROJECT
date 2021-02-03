package jm.stockx.rest_controller;

import com.google.gson.Gson;
import jm.stockx.ItemInfoService;
import jm.stockx.SellingInfoService;
import jm.stockx.dto.itemInfo.ItemInfoDto;
import jm.stockx.dto.itemInfo.ItemInfoDtoDecimal;
import jm.stockx.dto.itemInfo.ItemSearchDto;
import jm.stockx.dto.sellingInfo.ItemTopInfoDto;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {
    private final ItemInfoService itemInfoService;
    private final SellingInfoService sellingInfoService;
    private static final Gson gson = new Gson();

    public ItemRestController(ItemInfoService itemInfoService,
                              SellingInfoService sellingInfoService) {
        this.itemInfoService = itemInfoService;
        this.sellingInfoService = sellingInfoService;
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

    @GetMapping("/mostpopular")
    public ResponseEntity<List<ItemTopInfoDto>> getMostPopularItems() {
        List<ItemTopInfoDto> foundTodItems = sellingInfoService.getItemTopInfoDto(5);
        return ResponseEntity.ok(foundTodItems);
    }
}
