package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BrandService;
import jm.stockx.ShoeSizeService;
import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.dto.ShoeSizePutDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.ShoeSize;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
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
    public Response<List<ShoeSizeDto>> getAllShoeSize() {
        List<ShoeSizeDto> shoeSizeDtos = new LinkedList<>();
        List<ShoeSize> shoeSizeList = shoeSizeService.getAll();
        for (ShoeSize shoesize : shoeSizeList
        ) {
            ShoeSizeDto shoeSizeDto = new ShoeSizeDto(shoesize);
            shoeSizeDtos.add(shoeSizeDto);

        }
        logger.info("Получен список размеров обуви. Всего {} записей.", shoeSizeDtos.size());
        return Response.ok(shoeSizeDtos);
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
    public Response<ShoeSizeDto> getShoeSizeById(@PathVariable Long id) {
        if (shoeSizeService.isShoeSizeExist(id)) {
            ShoeSizeDto shoeSizeDto = shoeSizeService.getShoeSizedDtoById(id);
            logger.info("Получен размер обуви {} ", shoeSizeDto);
            return Response.ok(shoeSizeDto);
        }
        logger.warn("Размер обуви с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Shoe_size not found");
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
    public Response<?> createShoeSize(@RequestBody ShoeSize shoeSize) {
        String shoeSizeName = String.valueOf(shoeSize.getSize());
        if (shoeSizeService.isShoeSizeExist(shoeSize.getId())) {
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
    public Response<?> updateShoeSize(@RequestBody ShoeSizePutDto shoeSizePutDto) {
        String shoeSizeName = String.valueOf(shoeSizePutDto.getSize());
        if (shoeSizeService.isShoeSizeExist(shoeSizePutDto.getId())) {
            ShoeSize shoeSizeUpdate = new ShoeSize(shoeSizePutDto.getId(), shoeSizePutDto.getSize(), shoeSizePutDto.getSizeTypes());
            shoeSizeService.update(shoeSizeUpdate);
            logger.info("Размер обуви {} успешно обновлен", shoeSizeName);
            return Response.ok().build();
        }
        logger.warn("Размер обуви {} в базе не найден", shoeSizeName);
        return Response.error(HttpStatus.BAD_REQUEST, "ShoeSize not found");
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
    public Response<?> deleteShoeSize(@PathVariable Long id) {
        if (shoeSizeService.isShoeSizeExist(id)) {
            shoeSizeService.delete(id);
            logger.info("Размер обуви с id = {} успешно удалён", id);
            return Response.ok().build();
        }
        logger.warn("Размер обуви с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Shoe_size not found");
    }
}
