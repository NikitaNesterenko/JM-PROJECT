package jm.stockx.api.dao;

import jm.stockx.dto.bid.BidDto;
import jm.stockx.entity.Bid;

public interface BidDAO extends GenericDao<Bid, Long> {
    BidDto getBidDtoByBidId(Long id);

    BidDto getBidDtoByItemNameAndUserName(String itemName, String userName);
}
