package jm.stockx.api.dao;

import jm.stockx.dto.SubscriptionDto;
import jm.stockx.entity.Subscription;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDAOImpl extends AbstractDAO<Subscription, Long> implements SubscriptionDAO  {

    @Override
    public SubscriptionDto getSubscriptionDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.SubscriptionDto(" +
                "s.id," +
                "s.subscribers," +
                "s.items)" +
                "FROM Subscription AS s " +
                "WHERE s.id =: id", SubscriptionDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
