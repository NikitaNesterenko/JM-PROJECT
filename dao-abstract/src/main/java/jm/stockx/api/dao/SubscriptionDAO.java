package jm.stockx.api.dao;

import jm.stockx.dto.SubscriptionDto;
import jm.stockx.entity.Subscription;

public interface SubscriptionDAO extends GenericDao<Subscription, Long> {
    SubscriptionDto getSubscriptionDtoById(Long id);
}
