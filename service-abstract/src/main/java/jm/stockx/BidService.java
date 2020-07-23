package jm.stockx;

import jm.stockx.entity.Bid;

import java.util.List;

public interface BidService {

    List<Bid> getAll();

    Bid get(Long id);

    void create(Bid bid);

    void update(Bid bid);

    void delete(Long id);
}
