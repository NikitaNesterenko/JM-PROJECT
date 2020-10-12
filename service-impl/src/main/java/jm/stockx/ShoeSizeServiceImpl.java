package jm.stockx;

import jm.stockx.api.dao.ShoeSizeDAO;
import jm.stockx.dto.shoeSize.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ShoeSizeServiceImpl implements ShoeSizeService {

    private final ShoeSizeDAO shoeSizeDAO;

    @Autowired
    public ShoeSizeServiceImpl(ShoeSizeDAO shoeSizeDAO) {
        this.shoeSizeDAO = shoeSizeDAO;
    }


    @Override
    public List<ShoeSize> getAll() {
        return shoeSizeDAO.getAll();
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id) {
        return shoeSizeDAO.getShoeSizeDtoByShoeSizeId(id);
    }

    @Override
    public void create(ShoeSize shoeSize) {
        shoeSizeDAO.add(shoeSize);
    }

    @Override
    public void delete(Long id) {
        shoeSizeDAO.deleteById(id);
    }

    @Override
    public void update(ShoeSize shoeSize) {
        shoeSizeDAO.update(shoeSize);
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name) {
        return shoeSizeDAO.getShoeSizeDtoByShoeSizeName(name);
    }

    @Override
    public boolean isShoeSizeExist(Long id) {
        return shoeSizeDAO.doesItExistEntity(id);
    }

    @Override
    public List<ShoeSizeDto> getShoeSizeDtoByShoeSizeType(ShoeSizeTypes sizeType) {
        List<ShoeSizeDto> list = new ArrayList<>();
        shoeSizeDAO.getShoeSizeDtoByShoeSizeType(sizeType).forEach(x -> list.add(new ShoeSizeDto(x)));
        return list;
    }

}
