package jm.stockx.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jm.stockx.BidException;
import jm.stockx.BidService;
import jm.stockx.dto.bid.BidDto;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/bid")
public class BidRestController {
    private final BidService bidService;

    @Autowired
    public BidRestController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    @Operation(
            operationId = "placeBid",
            description =
                    "Takes bidDto and checks if Bid already exists, If exists price is updated, if does not already exists, new bid is placed",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "newBid", description = "Bid placement Request",
                            content = @Content(schema = @Schema(implementation = BidPostDto.class)))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation"),
            }
    )
    public Response<String> placeBid(@RequestBody BidPostDto newBid) throws BidException {
        if (bidService.isBidByCurrentUserExist(newBid.getId(), newBid.getUserId())) {
            bidService.updateBidPrice(newBid.getPrice(), newBid.getId());
            return Response.accepted().body("Bid price was successfully updated");
        }
        bidService.placeBid(newBid);
        return Response.accepted().body("Bid placed");
    }

    /**
     * Получение самых высоких ставок.
     * Используется для заполнения компонента Main Page.
     *
     * @return List<BidDto>
     */
    @GetMapping(value = "/highest")
    public Response<List<BidDto>> getBids() {
        List<BidDto> highestBidsFound = bidService.getHighestBids();
        return Response.ok(highestBidsFound);
    }
}