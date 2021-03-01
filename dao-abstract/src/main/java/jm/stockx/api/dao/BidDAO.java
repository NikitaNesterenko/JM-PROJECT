package jm.stockx.api.dao;

import jm.stockx.dto.bid.BidDto;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.entity.Bid;
import org.joda.money.Money;

import java.util.List;

public interface BidDAO extends GenericDao<Bid, Long> {

    void addBid(BidPostDto bidPostDto);

    BidDto getBidDtoByBidId(Long id);

    BidDto getBidDtoByItemNameAndUserName(String itemName, String userName);

    void updateBidPrice(Money price, Long brandId);

    List<BidDto> getHighestBids();
}