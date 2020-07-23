package jm.stockx.controller.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BidService;
import jm.stockx.entity.Bid;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/user/bids")
@Tag(name = "bid", description = "Bid API")
@Slf4j
public class UserBidRestController {

    private final BidService bidService;

    public UserBidRestController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping(value = "/{id}")
    @Operation(
            operationId = "getBid",
            summary = "Get bid by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Bid.class)
                            ),
                            description = "OK: got bid"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no bid found")
            })
    public Response<Bid> getBidById(@PathVariable("id") Long id) {
        Bid bid = bidService.get(id);
        if (bid == null) {
            log.warn("Ставка с id = {} в базе не найдена", id);
            return Response.error(HttpStatus.BAD_REQUEST, "Bid not found");
        }
        log.info("Получена ставка {} ", bid);
        return Response.ok(bid);
    }

    @GetMapping
    @Operation(
            operationId = "getAllBids",
            summary = "Get all bids",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Bid.class)
                            ),
                            description = "OK: bids list received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no bids found")
            })
    public Response<List<Bid>> getAllBids() {
        List<Bid> bids = bidService.getAll();
        log.info("Получен список ставок. Всего {} записей.", bids.size());
        return Response.ok(bids);
    }

    @PostMapping
    @Operation(
            operationId = "addBid",
            summary = "Add bid",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Bid.class)
                            ),
                            description = "OK: bid added"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: bid was not added")
            })
    public Response<?> addBid(Bid bid) {
        bidService.create(bid);
        log.info("Ставка успешно добавлена");
        return Response.ok().build();
    }
}
