package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;
import jm.stockx.entity.User;

import java.util.List;

public interface SellingDAO extends GenericDao<SellingInfo, Long> {
    List<User> getTop20SellingUsers();
}
