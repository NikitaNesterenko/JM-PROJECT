package jm.stockx.api.dao;

import jm.stockx.dto.BidDto;
import jm.stockx.entity.Bid;
import org.springframework.stereotype.Repository;

@Repository
public class BidDaoImpl extends AbstractDAO<Bid, Long> implements BidDAO {
    @Override
    public BidDto getBidDtoById(Long id) {
        String query = "" +
                "SELECT new jm.stockx.dto.BidDto(" +
                "b.id," +
                "b.price," +
                "b.success)" +
                "FROM Bid AS b " +
                "WHERE id =: id";

        return entityManager.createQuery(query, BidDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
