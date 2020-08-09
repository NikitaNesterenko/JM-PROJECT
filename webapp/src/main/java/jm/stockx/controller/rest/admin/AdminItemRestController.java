package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.ItemService;
import jm.stockx.entity.Item;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin/items")
@Tag(name = "item", description = "Item API")
@Slf4j
public class AdminItemRestController {
    private final ItemService itemService;

    @Autowired
    public AdminItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
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
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: item was not created")
            })
    public Response<?> createItem(@Valid @RequestBody Item item) {
        String itemName = item.getName();
        if (itemService.isItemExist(item.getId())) {
            log.warn("Товар {} уже существует в базе", itemName);
            return Response.error(HttpStatus.BAD_REQUEST, "This item already exists in database");
        }
        itemService.create(item);
        log.info("Товар {} успешно создан", itemName);
        return Response.ok(item);
    }

    @PutMapping
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
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: unable to update item")
            })
    public Response<?> updateItem(@Valid @RequestBody Item item) {
        String itemName = item.getName();
        if (itemService.getItemByName(itemName) == null) {
            log.warn("Товар {} в базе не найден", itemName);
            return Response.error(HttpStatus.BAD_REQUEST, "Item not found");
        }
        itemService.update(item);
        log.info("Товар {} успешно обновлен", itemName);
        return Response.ok(item);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            operationId = "deleteItem",
            summary = "Delete item",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: item deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: no item with such id")
            }
    )
    public Response<?> deleteItem(@PathVariable Long id) {
        if (itemService.isItemExist(id)) {
            itemService.delete(id);
            log.info("Товар с id = {} успешно удален", id);
            return Response.ok().build();
        }
        log.warn("Товар с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Item not found");
    }


    @PostMapping(value = "/addItemImage")
    public Response<?> addItemImage(@RequestParam MultipartFile file, @RequestParam Long id) {
        Item item = itemService.get(id);
        String fileName = file.getOriginalFilename();
        String uploadRootPath = AdminItemRestController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        item.setItemImageUrl(uploadRootPath + item.getName());
        itemService.update(item);
        File uploadRootDir = new File(uploadRootPath + item.getName());

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        List<File> uploadedFiles = new ArrayList<File>();
        if (fileName != null && fileName.length() > 0) {
            try {
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
                uploadedFiles.add(serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + fileName);
                log.warn("Картинка не загружена");
                return Response.error(HttpStatus.BAD_REQUEST, "Image not loaded");
            }
        }
        return Response.ok().build();
    }
}
