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
    public List<SellerTopInfoDto> getTop20SellingUsers() {
        return sellingDAO.getTop20SellingUsers()
                .stream()
                .map(m -> new SellerTopInfoDto(m.getUsername()))
                .collect(Collectors.toList());
    }
}
