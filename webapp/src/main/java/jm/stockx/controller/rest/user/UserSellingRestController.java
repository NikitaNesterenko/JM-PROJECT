package jm.stockx.controller.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.SellingService;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.Status;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/user/sell")
@Tag(name = "sell", description = "Sell API")
@Slf4j
public class UserSellingRestController {
    private final SellingService sellingService;

    @Autowired
    public UserSellingRestController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @PutMapping(value = "/{id}")
    @Operation(
            operationId = "updateSellingStatus",
            summary = "Update Selling status by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SellingInfo.class)
                            ),
                            description = "OK: sellingInfo updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update sellingInfo")
            })
    public Response<?> updateSellingStatusById(@PathVariable("id") Long id, Status status) {
        sellingService.updateSellingStatus(id, status);
        log.info("Информация о продаже успешно обновлена");
        return Response.ok().build();
    }
}
