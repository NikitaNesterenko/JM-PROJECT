package jm.stockx;

import jm.stockx.api.dao.BrandDAO;
import jm.stockx.dto.brand.BrandDto;
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

    public Brand getBrandById(Long id) {
        return brandDAO.getById(id);
    }

    @Override
    public BrandDto get(Long id) {
        return brandDAO.getBrandDtoByBrandId(id);
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
    public BrandDto getBrandDtoByBrandName(String name) {
        return brandDAO.getBrandDtoByBrandName(name);
    }

    @Override
    public boolean isBrandExist(Long id) {
        return brandDAO.doesItExistEntity(id);
    }

    @Override
    public BrandDto getBrandDtoByBrandId(Long id) {
        return brandDAO.getBrandDtoByBrandId(id);
    }

    @Override
    public BrandDto getBrandByName(String name) {
        return brandDAO.getBrandByName(name);
    }

    @Override
    public List<BrandDto> getPopularBrands() {
        return brandDAO.getPopularBrands();
    }

    @Override
    public List<BrandDto> getPopularBrandsInTwoMonths() {
        return brandDAO.getPopularBrandsInTwoMonths();
    }
}