package jm.stockx;

import jm.stockx.entity.SellingInfo;

import java.util.List;

public interface SellingInfoService {

    Double getAverageSalesValue();

    List<SellingInfo> getAll();

    SellingInfo get(Long id);

    void create(SellingInfo sellingInfo);

    void update(SellingInfo sellingInfo);

    void delete(Long id);
}
