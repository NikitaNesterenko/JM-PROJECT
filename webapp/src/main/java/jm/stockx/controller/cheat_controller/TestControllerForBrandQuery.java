package jm.stockx.controller.cheat_controller;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.BrandService;
import jm.stockx.SellingInfoService;
import jm.stockx.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;

@Route("brand")
public class TestControllerForBrandQuery extends VerticalLayout {
    private final BrandService brandService;
    private SellingInfoService sellingInfoService;
    private final Grid<Brand> grid;

    @Autowired
    public TestControllerForBrandQuery(BrandService brandService, SellingInfoService sellingInfoService) {
        this.brandService = brandService;
        this.sellingInfoService = sellingInfoService;
        this.grid = new Grid<>(Brand.class);

        addClassName("list-view");
        setSizeFull();

        add(grid);
        configureGrid();
        updateList();
    }

    private void configureGrid() {
        grid.setClassName("brand-grid");
    }

    private void updateList() {
        grid.setItems(brandService.getPopularBrandIn2Month());
    }
}
