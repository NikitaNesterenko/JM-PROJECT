package jm.stockx;

import jm.stockx.dto.BidDto;
import jm.stockx.dto.BidPostDto;
import jm.stockx.entity.Bid;

import java.util.List;

public interface BidService {

    List<Bid> getAll();

    BidDto get(Long id);

    BidDto get(String itemName, String userName);

    void create(Bid bid);

    void create(BidPostDto bidPostDto);

    void update(Bid bid);

    void delete(Long id);

    Boolean isBidExist(Long id);
}
