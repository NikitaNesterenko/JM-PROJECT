package jm.stockx;

import jm.stockx.api.dao.SellingDAO;
import jm.stockx.dto.SellerTopInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellingInfoServiceImpl implements SellingInfoService{

    private final SellingDAO sellingDAO;

    @Autowired
    public SellingInfoServiceImpl(SellingDAO sellingDAO){
        this.sellingDAO = sellingDAO;
    }

    @Override
    public List<SellerTopInfoDto> getTopSellingUsers() {
        return sellingDAO.getTopSellingUsers()
                .stream()
                .map(m -> new SellerTopInfoDto(m.getUsername(), sellingDAO.getCountOfSellByUserId(m.getId())))
                .collect(Collectors.toList());
    }
}
