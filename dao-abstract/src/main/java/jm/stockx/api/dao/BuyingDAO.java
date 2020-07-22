package jm.stockx.api.dao;

import jm.stockx.entity.BuyingInfo;

import java.util.List;

public interface BuyingDAO {

    List<BuyingInfo> getAll();

    BuyingInfo getById(Long id);

    void add(BuyingInfo buyingInfo);

    void deleteById(Long id);

}
