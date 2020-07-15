package jm;

import jm.api.dao.BrandDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandDAO brandDAO;

    public BrandServiceImpl(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    @Override
    public List<Brand> getAll() {
        return brandDAO.getAll();
    }

    @Override
    public Brand get(Long id) {
        return brandDAO.getById(id);
    }

    @Override
    public void create(Brand brand) {
        brandDAO.add(brand);
    }

    @Override
    public void update(Brand brand) {
        brandDAO.merge(brand);
    }

    @Override
    public void delete(Long id) {
        brandDAO.deleteById(id);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandDAO.getBrandByName(name).orElse(null);
    }
}
