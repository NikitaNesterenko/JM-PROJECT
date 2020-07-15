package jm;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandService brandService;

    public BrandServiceImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @Override
    public Brand get(Long id) {
        return brandService.get(id);
    }

    @Override
    public void create(Brand brand) {
        brandService.create(brand);
    }

    @Override
    public void update(Brand brand) {
        brandService.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandService.delete(id);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandService.getBrandByName(name);
    }
}
