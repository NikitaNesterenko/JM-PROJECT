package jm.stockx;

import jm.stockx.api.dao.BidDAO;
import jm.stockx.api.dao.ItemDAO;
import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.UserDAO;
import jm.stockx.dto.bid.BidDto;
import jm.stockx.dto.bid.BidPostDto;
import jm.stockx.entity.Bid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final BidDAO bidDAO;
    private final ItemInfoDAO itemInfoDAO;
    private final UserDAO userDAO;

    public BidServiceImpl(BidDAO bidDAO, ItemDAO itemDAO, ItemInfoDAO itemInfoDAO, UserDAO userDAO) {
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
    public void create(Bid bid) {
        bidDAO.add(bid);
    }

    @Override
    public void create(BidPostDto bidPostDto) {
        Bid bid = new Bid();
        bid.setPrice(bidPostDto.getPrice());
        bid.setSuccess(bidPostDto.getSuccess());
        bid.setItemInfo(itemInfoDAO.getItemInfoByItemName(bidPostDto.getItemName()));
        bid.setUser(userDAO.getUserByUsername(bidPostDto.getUserName()));
        bidDAO.add(bid);
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
}
