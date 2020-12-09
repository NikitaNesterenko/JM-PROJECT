package jm.stockx.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jm.stockx.BidAdviceException;
import jm.stockx.BidService;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bid")

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
    public Response<?> placeBid(@RequestBody BidPostDto newBid) throws BidAdviceException {
        try {
            if (bidService.isBidByCurrentUserExist(newBid.getId(), newBid.getUserId())){
                bidService.updateBidPrice(newBid.getPrice(),newBid.getId());
                return Response.ok(HttpStatus.ACCEPTED,"Bid price was successfully updated");
            }
            bidService.placeBid(newBid);
            return Response.ok(HttpStatus.ACCEPTED, "Bid placed");
        } catch (BidAdviceException e) {
            throw new BidAdviceException();
        }
    }
}
