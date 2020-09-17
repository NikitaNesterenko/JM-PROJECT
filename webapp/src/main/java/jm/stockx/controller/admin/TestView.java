package jm.stockx.controller.admin;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jm.stockx.BrandService;
import jm.stockx.entity.Brand;

@Route("brand/all")
public class TestView extends VerticalLayout {

    private final BrandService brandService;
    final Grid<Brand> grid;

    public TestView(BrandService brandService) {
        this.brandService = brandService;
        this.grid = new Grid<>(Brand.class);
        add(grid);
        listCustomers();
    }

    private void listCustomers() {
        grid.setItems(brandService.getAll());
    }
}
