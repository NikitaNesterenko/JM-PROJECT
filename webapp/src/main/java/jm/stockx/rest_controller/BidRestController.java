package jm.stockx.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jm.stockx.BidService;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.entity.Bid;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            description = "Takes bidDto from Client and passes to bidService",
            parameters = {@Parameter(in = ParameterIn.QUERY, name = "newBid",
                    description = "Bid placement Request",
                    content = @Content(schema = @Schema(implementation = BidPostDto.class)))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied")
            }
    )
    public Response<?> placeBid(@RequestBody BidPostDto newBid) {
        bidService.placeBid(newBid);
        return Response.ok(HttpStatus.ACCEPTED, "Bid placed");
    }
}
