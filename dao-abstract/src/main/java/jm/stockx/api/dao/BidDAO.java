package jm.stockx.api.dao;

import jm.stockx.dto.BidDto;
import jm.stockx.entity.Bid;

public interface BidDAO extends GenericDao<Bid, Long> {
    BidDto getBidDtoById(Long id);
}
