package jm.controller.item;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jm.Item;
import jm.ItemService;
import jm.component.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/items")
public class ItemRestController {
    private static final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    private final ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/get")
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
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no items found")
            })
    public Response<List<Item>> getAllItems() {
        List<Item> items = itemService.getAll();
        logger.info("Получен список товаров. Всего {} записей.", items.size());
        return Response.ok(items);
    }

    @GetMapping(value = "/get/{id}")
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
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no items with this item id")
            })
    public Response<Item> getItemById(@PathVariable("id") Long id) {
        Item item = itemService.get(id);
        if (item == null) {
            logger.warn("Товар с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "Item not found");
        }
        logger.info("Получен товар {} ", item);
        return Response.ok(item);
    }

    @PostMapping(value = "/create")
    @Operation(
            operationId = "createItem",
            summary = "Create item",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Item.class)
                            ),
                            description = "OK: item created"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: item was not created")
            })
    public Response<?> createItem(Item item) {
        String itemName = item.getName();
        if (itemService.getItemByName(itemName) != null) {
            logger.warn("Товар {} уже существует в базе", itemName);
            return Response.error(HttpStatus.BAD_REQUEST,"This item already exists in database");
        }
        itemService.create(item);
        logger.info("Товар {} успешно создан", itemName);
        return Response.ok().build();
    }

    @PutMapping(value = "/update")
    @Operation(
            operationId = "updateItem",
            summary = "Update item",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Item.class)
                            ),
                            description = "OK: item updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update item")
            })
    public Response<?> updateItem(Item item) {
        String itemName = item.getName();
        if (itemService.getItemByName(itemName) == null) {
            logger.warn("Товар {} в базе не найден", itemName);
            return Response.error(HttpStatus.BAD_REQUEST,"Item not found");
        }
        itemService.update(item);
        logger.info("Товар {} успешно обновлен", itemName);
        return Response.ok().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(
            operationId = "deleteItem",
            summary = "Delete item",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: item deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT FOUND: no item with such id")
            }
    )
    public Response<?> deleteItem(@PathVariable("id") Long id) {
        if (itemService.get(id) == null) {
            logger.warn("Товар с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST,"Item not found");
        }
        itemService.delete(id);
        logger.info("Товар с id = {} успешно удален", id);
        return Response.ok().build();
    }
}
