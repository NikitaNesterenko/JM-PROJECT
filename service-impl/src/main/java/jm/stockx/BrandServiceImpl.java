package jm.stockx;

import jm.stockx.api.dao.BrandDAO;
import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;
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
    public BrandDto get(Long id) {
        return brandDAO.getBrandDtoById(id);
    }

    @Override
    public void create(Brand brand) {
        brandDAO.add(brand);
    }

    @Override
    public void update(Brand brand) {
        brandDAO.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandDAO.deleteById(id);
    }

    @Override
    public BrandDto getBrandByName(String name) {
        return brandDAO.getBrandDtoByName(name);
    }

    @Override
    public boolean isBrandExist(Long id) {
        return brandDAO.doesItExistEntity(id);
    }

    @Override
    public BrandDto getBrandDtoById(Long id) {
        return brandDAO.getBrandDtoById(id);
    }

    @Override
    public Brand getBrand(String name) {
        return brandDAO.getBrand(name);
    }

    @Override
    public List<Brand> getPopularBrandIn2Month() {
        return brandDAO.getPopularBrandIn2Month();
    }
