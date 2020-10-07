package jm.stockx;

import jm.stockx.dto.BidDto;
import jm.stockx.dto.BidPostDto;
import jm.stockx.entity.Bid;

import java.util.List;
import java.util.Set;

public interface BidService {

    Set<Bid> getAll();

    BidDto get(Long id);

    BidDto get(String itemName, String userName);

    void create(Bid bid);

    void create(BidPostDto bidPostDto);

    void update(Bid bid);

    void delete(Long id);

    Boolean isBidExist(Long id);
}
