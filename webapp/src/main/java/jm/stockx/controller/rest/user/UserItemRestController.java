package jm.stockx.controller.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BrandService;
import jm.stockx.ItemService;
import jm.stockx.dto.BuyingDto;
import jm.stockx.dto.ItemDto;
import jm.stockx.dto.PageDto;
import jm.stockx.entity.Item;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/user/items")
@Tag(name = "item", description = "Item API")
@Slf4j
public class UserItemRestController {

    private final ItemService itemService;
    private final BrandService brandService;

    @Autowired
    public UserItemRestController(ItemService itemService, BrandService brandService) {
        this.itemService = itemService;
        this.brandService = brandService;
    }

    @GetMapping
    @Operation(
            operationId = "getAllItems",
            summary = "Get all items",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Item.class)
                            ),
                            description = "OK: got items list"
                    ),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: no items found")
            })
    public Response<List<Item>> getAllItems() {
        List<Item> items = itemService.getAll();
        log.info("Получен список товаров. Всего {} записей.", items.size());
        return Response.ok(items);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            operationId = "getItemById",
            summary = "Get item by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Item.class)
                            ),
                            description = "OK: got item"
                    ),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: no items with this item id")
            })
    public Response<Item> getItemById(@PathVariable("id") Long id) {
        Item item = itemService.get(id);
        if (item == null) {
            log.warn("Товар с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "Item not found");
        }
        log.info("Получен товар {} ", item);
        return Response.ok(item);
    }

    @GetMapping("/itemInfo")
    public Response<PageDto<ItemDto>> getItemInformation(@RequestParam(name = "page") Integer page,
                                                         @RequestParam(name = "search") String search,
                                                         @RequestParam(required = false, defaultValue = "15") Integer size) {
        PageDto<ItemDto> pageDto = itemService.getPageOfItems(page, search, size);
        return Response.ok(pageDto);
    }

    @GetMapping("/buy")
    public Response<?> buyItemNow(@Valid BuyingDto buyingDto) {
        itemService.buyItem(buyingDto);
        return Response.ok(buyingDto);
    }

    @GetMapping("/top")
    public Response<List<Item>> getTopItemsByStyle(@RequestParam(name = "styleId") Long styleId,
                                                   @RequestParam(name = "limit", required = false, defaultValue = "5") Integer limit) {
        List<Item> items = itemService.getTopItemsByStyle(styleId, limit);
        return Response.ok(items);
    }

    @GetMapping("/unreleased")
    public Response<List<Item>> getUnreleasedItems() {
        List<Item> items = itemService.getNotReleasedItems();
        return Response.ok(items);
    }

    @GetMapping("/unreleasedBrand")
    public Response<List<Item>> getUnreleasedItemsByBrand(@RequestParam(name = "brandName") String brandName) {
        List<Item> items = itemService.getNotReleasedItemsByBrand(brandService.getBrandByName(brandName));
        return Response.ok(items);
    }
}
