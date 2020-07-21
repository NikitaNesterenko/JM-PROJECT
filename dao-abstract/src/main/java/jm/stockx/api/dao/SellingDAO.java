package jm.stockx.api.dao;

import jm.stockx.entity.SellingInfo;

import java.util.List;

public interface SellingDAO {

    List<SellingInfo> getAll();

    SellingInfo getById(Long id);

    void add(SellingInfo sellingInfo);

    void deleteById(Long id);
}
