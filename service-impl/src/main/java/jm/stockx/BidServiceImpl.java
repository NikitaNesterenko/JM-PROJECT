package jm.stockx;

import jm.stockx.api.dao.BidDAO;
import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.bid.BidDto;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.entity.Bid;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final BidDAO bidDAO;
    private final ItemInfoDAO itemInfoDAO;
    private final UserDAO userDAO;

    public BidServiceImpl(BidDAO bidDAO,
                          ItemInfoDAO itemInfoDAO,
                          UserDAO userDAO) {
        this.bidDAO = bidDAO;
        this.itemInfoDAO = itemInfoDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Bid> getAll() {
        return bidDAO.getAll();
    }

    @Override
    public BidDto getBidDtoByBidId(Long id) {
        return bidDAO.getBidDtoByBidId(id);
    }

    @Override
    public BidDto getBidDtoByItemNameAndUserName(String itemName, String userName) {
        return bidDAO.getBidDtoByItemNameAndUserName(itemName, userName);
    }

    @Override
    public void update(Bid bid) {
        bidDAO.update(bid);
    }

    @Override
    public void delete(Long id) {
        bidDAO.deleteById(id);
    }

    @Override
    public Boolean isBidExist(Long id) {
        return bidDAO.doesItExistEntity(id);
    }

    @Override
    public void updateBidPrice(String price, Long id) {
        bidDAO.updateBidPrice(Money.parse(price), id);
    }

    @Override
    public List<BidDto> getHighestBids() {
        return bidDAO.getHighestBids();
    }

    public boolean isBidByCurrentUserExist(Long bidId, Long userId) {
        return isBidExist(bidId) && userDAO.doesItExistEntity(userId);
    }

    /**
     * Builds BidEntity from BidDto and saves it to Database
     *
     * @param newBid bidDto from controller
     */
    @Override
    public void placeBid(BidPostDto newBid) {
        bidDAO.addBid(newBid);
    }
}