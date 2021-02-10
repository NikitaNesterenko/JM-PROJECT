package jm.stockx.rest_controller;

import jm.stockx.BrandService;
import jm.stockx.dto.brand.BrandDto;
import jm.stockx.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/brands")
public class BrandRestController {
    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Получение популярных брендов.
     * Используется для заполнения компонента Main Page.
     *
     * @return List<BrandDto>
     */
    @GetMapping(value = "/populars")
    public Response<List<BrandDto>> getPopularBrands() {
        List<BrandDto> brandsFound = brandService.getPopularBrands();
        return Response.ok(brandsFound);
    }
}