package jm.controller.brand;

import io.swagger.v3.oas.annotations.tags.Tag;
import jm.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/brand")
@Tag(name = "brand", description = "Brand API")
public class BrandRestController {

    private static final Logger logger = LoggerFactory.getLogger(BrandRestController.class);

    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }
}
