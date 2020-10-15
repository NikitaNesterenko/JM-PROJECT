package jm.stockx;

import jm.stockx.api.dao.ShoeSizeDAO;
import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public ShoeSizeDto get(Long id) {
        return shoeSizeDAO.getShoeSizeDtoById(id);
    }

    @Override
    public ShoeSize findOneBySize(Double size) {
        return shoeSizeDAO.findOneBySize(size);
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
    public ShoeSizeDto getShoeSizeByName(String name) {
        return shoeSizeDAO.getShoeSizeDtoByName(name);
    }

    @Override
    public boolean isShoeSizeExist(Long id) {
        return shoeSizeDAO.doesItExistEntity(id);
    }

    @Override
    public ShoeSizeDto getShoeSizedDtoById(Long id) {
        return shoeSizeDAO.getShoeSizeDtoById(id);
    }

    @Override
    public List<ShoeSizeDto> getShoeSizeDtoBySizeType(ShoeSizeTypes sizeType) {
        List<ShoeSizeDto> list = new ArrayList<>();
        shoeSizeDAO.getShoeSizeDtoBySizeType(sizeType).forEach(x -> list.add(new ShoeSizeDto(x)));
        return list;
    }

}
