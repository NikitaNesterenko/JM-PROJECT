package jm.stockx.initializer.initmodels;

import jm.stockx.BrandService;
import jm.stockx.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания брендов в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class BrandInit {
    private final BrandService brandService;

    public void initializeBrands() {
        List<Brand> brandsListForCreation = createBrandsForInitialization();
        brandsListForCreation.forEach(brandService::create);
    }

    private List<Brand> createBrandsForInitialization() {
        return Arrays.asList(
                new Brand("Adidas"),
                new Brand("Converse"),
                new Brand("Jordan"),
                new Brand("Louis Vuitton"),
                new Brand("New Balance"),
                new Brand("Nike"),
                new Brand("Saucony"),
                new Brand("Vans")
        );
    }
}