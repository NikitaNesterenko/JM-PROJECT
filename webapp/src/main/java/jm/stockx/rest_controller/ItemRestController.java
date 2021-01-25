package jm.stockx.rest_controller;

import com.google.gson.Gson;
import jm.stockx.ItemInfoService;
import jm.stockx.dto.itemInfo.ItemInfoDto;
import jm.stockx.dto.itemInfo.ItemInfoDtoDecimal;
import jm.stockx.dto.itemInfo.ItemSearchDto;
import jm.stockx.entity.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {
    private final ItemInfoService itemInfoService;
    private static final Gson gson = new Gson();

    @Autowired
    public ItemRestController(ItemInfoService itemInfoService) {
        this.itemInfoService = itemInfoService;
    }

    @GetMapping("/search")
    public List<ItemSearchDto> searchItem(@RequestParam(required = false, name = "s") String search) {
        return itemInfoService.getItemSearchDtoBySearch(search);
    }

    @GetMapping("/allItem")
    public ResponseEntity<List<ItemInfoDtoDecimal>> getAllItemInfo(){

        List<ItemInfoDtoDecimal> list = itemInfoService.getAllItemInfoDtoDecimal();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/item")
    public ResponseEntity<ItemInfoDtoDecimal> getItemInfoDtoDec(@RequestParam (name = "id") Long id){
        return ResponseEntity.ok(itemInfoService.getItemInfoDtoDec(id));
    }



}
