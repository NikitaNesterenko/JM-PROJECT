package jm.stockx.rest_controller;

import jm.stockx.RecommendedItemsService;
import jm.stockx.dto.recommendedItem.RecommendedItemDto;
import jm.stockx.enums.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rest/recommended")
public class RecommendedItemsController {

    private final RecommendedItemsService recommendedItemsService;

    @Autowired
    public RecommendedItemsController(RecommendedItemsService recommendedItemsService) {
        this.recommendedItemsService = recommendedItemsService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<RecommendedItemDto>> getRecommendedItems(@RequestParam ItemCategory itemCategory) {

        List<RecommendedItemDto> recommendedItems = recommendedItemsService.get15RecommendedItems(itemCategory);
        return new ResponseEntity<>(recommendedItems, HttpStatus.OK);

    }

}
