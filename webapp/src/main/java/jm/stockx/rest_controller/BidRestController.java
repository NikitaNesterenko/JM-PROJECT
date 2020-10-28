package jm.stockx.rest_controller;

import jm.stockx.BidService;
import jm.stockx.entity.Bid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void placeBid(@RequestBody Bid newBid){

        bidService.placeBid(newBid);
    }

}
