package jm.stockx.controller.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.SellingInfoService;
import jm.stockx.dto.SellerTopInfoDto;
import jm.stockx.entity.User;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/selling")
@Tag(name = "selling", description = "Selling info")
@Slf4j
public class UserSellingInfoRestController {

    private final SellingInfoService sellingInfoService;

    @Autowired
    public UserSellingInfoRestController(SellingInfoService sellingInfoService){
        this.sellingInfoService = sellingInfoService;
    }

    @GetMapping("/top")
    @Operation(
            operationId = "getTopSellingUsers",
            summary = "Get top selling users",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = User.class)
                            ),
                            description = "OK: got top selling users"
                    ),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: no selling users with this request")
            })
    public Response<List<SellerTopInfoDto>> getTopSellingUsers(){
        List<SellerTopInfoDto> sellers = sellingInfoService.getTopSellingUsers();
        log.info("Получен список самых продающих селлеров: {}", sellers);
        return Response.ok(sellers);
    }
}
