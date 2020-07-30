package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BrandService;
import jm.stockx.ShoeSizeService;
import jm.stockx.entity.Brand;
import jm.stockx.entity.ShoeSize;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin/shoe_size")
@Tag(name = "shoe_size", description = "ShoesSize API")
@Slf4j
public class AdminShoeSizeRestController {

    private static final Logger logger = LoggerFactory.getLogger(AdminShoeSizeRestController.class);

    private final ShoeSizeService shoeSizeService;

    public AdminShoeSizeRestController(ShoeSizeService shoeSizeService) {
        this.shoeSizeService = shoeSizeService;
    }

    @GetMapping
    @Operation(
            operationId = "getAllShoeSize",
            summary = "Get all ShoeSize",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = ShoeSize.class)
                            ),
                            description = "OK: shoe_size list received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no shoe_size found")
            })
    public Response<List<ShoeSize>> getAllShoeSize() {
        List<ShoeSize> shoeSizeList = shoeSizeService.getAll();
        logger.info("Получен список размеров обуви. Всего {} записей.", shoeSizeList.size());
        return Response.ok(shoeSizeList);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            operationId = "getShoeSizeById",
            summary = "Get shoe_size by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = ShoeSize.class)
                            ),
                            description = "OK: shoe_size received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no shoe_size with this shoe_size-id")
            })
    public Response<ShoeSize> getShoeSizeById(@PathVariable("id") Long id) {
        ShoeSize shoeSize = shoeSizeService.get(id);
        if (shoeSize == null) {
            logger.warn("Размер обуви с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "Shoe_size not found");
        }
        logger.info("Получен размер обуви {} ", shoeSize);
        return Response.ok(shoeSize);
    }

    @PostMapping
    @Operation(
            operationId = "createShoeSize",
            summary = "Create shoe_size",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShoeSize.class)
                            ),
                            description = "OK: shoe_size created"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: shoe_size was not created")
            })
    public Response<?> createShoeSize(ShoeSize shoeSize) {
        String shoeSizeName = String.valueOf(shoeSize.getSize());
        if (shoeSizeService.getShoeSizeByName(shoeSizeName) != null) {
            logger.warn("Размер обуви> {} уже существует в базе", shoeSizeName);
            return Response.error(HttpStatus.BAD_REQUEST, "This shoe_size already exists in database");
        }
        shoeSizeService.create(shoeSize);
        logger.info("Размер обуви {} успешно создан", shoeSizeName);
        return Response.ok().build();
    }

    @PutMapping
    @Operation(
            operationId = "updateShoeSize",
            summary = "Update shoe_size",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShoeSize.class)
                            ),
                            description = "OK: shoe_size updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update shoe_size")
            })
    public Response<?> updateShoeSize(ShoeSize shoeSize) {
        String shoeSizeName = String.valueOf(shoeSize.getSize());
        if (shoeSizeService.getShoeSizeByName(shoeSizeName) == null) {
            logger.warn("Размер обуви {} в базе не найден", shoeSizeName);
            return Response.error(HttpStatus.BAD_REQUEST, "ShoeSize not found");
        }
        shoeSizeService.update(shoeSize);
        logger.info("Размер обуви {} успешно обновлен", shoeSizeName);
        return Response.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            operationId = "deleteShoeSize",
            summary = "Delete shoe_size",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: shoe_size deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT FOUND: no shoe_size with such id")
            }
    )
    public Response<?> deleteShoeSize(@PathVariable("id") Long id) {
        if (shoeSizeService.get(id) == null) {
            logger.warn("Размер обуви с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST, "Shoe_size not found");
        }
        shoeSizeService.delete(id);
        logger.info("Размер обуви с id = {} успешно удалён", id);
        return Response.ok().build();
    }
}
