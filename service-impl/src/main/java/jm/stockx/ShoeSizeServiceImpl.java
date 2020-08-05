package jm.stockx;

import jm.stockx.api.dao.ShoeSizeDAO;
import jm.stockx.entity.ShoeSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ShoeSize get(Long id) {
        return shoeSizeDAO.getById(id);
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
    public ShoeSize getShoeSizeByName(String name) {
        return shoeSizeDAO.getByName(name).orElse(null);
    }

    @Override
    public boolean isShoeSizeExist(Long id) {
        return shoeSizeDAO.doesItExistEntity(id);
    }
}
