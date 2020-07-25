package jm.stockx.api.dao;

import jm.stockx.entity.Bid;

public interface BidDAO extends GenericDao<Bid, Long> {
    boolean doesItExistEntity(Long id);
}