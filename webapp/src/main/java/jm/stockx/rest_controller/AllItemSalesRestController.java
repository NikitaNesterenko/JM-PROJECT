package jm.stockx.rest_controller;

import jm.stockx.AllItemSalesService;
import jm.stockx.dto.allitemsales.AllItemSalesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class AllItemSalesRestController {

    private final AllItemSalesService allItemSalesService;

    @Autowired
    public AllItemSalesRestController(AllItemSalesService allItemSalesService) {

        this.allItemSalesService = allItemSalesService;

    }

    @GetMapping("/sales/*")
    public ResponseEntity<List<AllItemSalesDto>> getSales(@RequestParam Long id) {

        List<AllItemSalesDto> list = allItemSalesService.getAllItemSalesById(id);

        return ResponseEntity.ok(list);

    }
}
