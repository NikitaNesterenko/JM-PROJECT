package jm.stockx.api.dao;

import jm.stockx.entity.BuyingInfo;
import org.springframework.stereotype.Repository;

@Repository
public class BuyingDAOImpl extends AbstractDAO<BuyingInfo, Long> implements BuyingDAO {
}
